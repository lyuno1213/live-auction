package io.bootify.live_auction.model;

import jakarta.validation.constraints.Size;


public class MemberDTO {

    private Integer id;

    @Size(max = 255)
    @MemberSignInIdUnique
    private String signInId;

    @Size(max = 255)
    @MemberPasswordUnique
    private String password;

    private Integer point;

    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getSignInId() {
        return signInId;
    }

    public void setSignInId(final String signInId) {
        this.signInId = signInId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(final Integer point) {
        this.point = point;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

}
