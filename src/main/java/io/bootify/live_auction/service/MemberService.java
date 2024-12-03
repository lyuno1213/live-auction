package io.bootify.live_auction.service;

import io.bootify.live_auction.domain.Member;
import io.bootify.live_auction.model.MemberDTO;
import io.bootify.live_auction.repos.MemberRepository;
import io.bootify.live_auction.util.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberService(final MemberRepository memberRepository, final MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public Page<MemberDTO> findAll(final String filter, final Pageable pageable) {
        Page<Member> page;
        if (filter != null) {
            Integer integerFilter = null;
            try {
                integerFilter = Integer.parseInt(filter);
            } catch (final NumberFormatException numberFormatException) {
                // keep null - no parseable input
            }
            page = memberRepository.findAllById(integerFilter, pageable);
        } else {
            page = memberRepository.findAll(pageable);
        }
        return new PageImpl<>(page.getContent()
                .stream()
                .map(member -> memberMapper.updateMemberDTO(member, new MemberDTO()))
                .toList(),
                pageable, page.getTotalElements());
    }

    public MemberDTO get(final Integer id) {
        return memberRepository.findById(id)
                .map(member -> memberMapper.updateMemberDTO(member, new MemberDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final MemberDTO memberDTO) {
        final Member member = new Member();
        memberMapper.updateMember(memberDTO, member);
        return memberRepository.save(member).getId();
    }

    public void update(final Integer id, final MemberDTO memberDTO) {
        final Member member = memberRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        memberMapper.updateMember(memberDTO, member);
        memberRepository.save(member);
    }

    public void delete(final Integer id) {
        memberRepository.deleteById(id);
    }

    public boolean signInIdExists(final String signInId) {
        return memberRepository.existsBySignInIdIgnoreCase(signInId);
    }

    public boolean passwordExists(final String password) {
        return memberRepository.existsByPasswordIgnoreCase(password);
    }

}
