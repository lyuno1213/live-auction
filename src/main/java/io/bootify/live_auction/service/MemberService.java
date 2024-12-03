package io.bootify.live_auction.service;

import io.bootify.live_auction.domain.member.Member;
import io.bootify.live_auction.model.MemberDTO;
import io.bootify.live_auction.repos.member.MemberRepository;
import io.bootify.live_auction.util.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Page<MemberDTO> findAll(final String filter, final Pageable pageable) {
        Page<Member> page;
        if (filter != null) {
            Long integerFilter = null;
            try {
                integerFilter = Long.parseLong(filter);
            } catch (final NumberFormatException numberFormatException) {
                // keep null - no parseable input
            }
            page = memberRepository.findAllById(integerFilter, pageable);
        } else {
            page = memberRepository.findAll(pageable);
        }
        return new PageImpl<>(page.getContent()
                .stream()
                .map(member -> mapToDTO(member, new MemberDTO()))
                .toList(),
                pageable, page.getTotalElements());
    }

    public MemberDTO get(final Integer id) {
        return memberRepository.findById(id)
                .map(member -> mapToDTO(member, new MemberDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final MemberDTO memberDTO) {
        final Member member = new Member();
        mapToEntity(memberDTO, member);
        return memberRepository.save(member).getId();
    }

    public void update(final Integer id, final MemberDTO memberDTO) {
        final Member member = memberRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(memberDTO, member);
        memberRepository.save(member);
    }

    public void delete(final Integer id) {
        memberRepository.deleteById(id);
    }

    private MemberDTO mapToDTO(final Member member, final MemberDTO memberDTO) {
        memberDTO.setId(member.getId());
        memberDTO.setSignInId(member.getSignInId());
        memberDTO.setPassword(member.getPassword());
        memberDTO.setPoint(member.getPoint());
        memberDTO.setRole(member.getRole());
        return memberDTO;
    }

    private Member mapToEntity(final MemberDTO memberDTO, final Member member) {
        member.setSignInId(memberDTO.getSignInId());
        member.setPassword(memberDTO.getPassword());
        member.setPoint(memberDTO.getPoint());
        member.setRole(memberDTO.getRole());
        return member;
    }

    public boolean passwordExists(String value) {
        return true;
    }

    public boolean signInIdExists(String value) {
        return true;
    }

//    public boolean signInIdExists(final String signInId) {
//        return memberRepository.existsBySignInIdIgnoreCase(signInId);
//    }
//
//    public boolean passwordExists(final String password) {
//        return memberRepository.existsByPasswordIgnoreCase(password);
//    }

}
