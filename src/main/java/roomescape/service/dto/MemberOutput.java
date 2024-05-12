package roomescape.service.dto;

import roomescape.domain.Member;

public class MemberOutput {
    private final Long id;
    private final String name;
    private final String email;

    public MemberOutput(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public MemberOutput(Member member) {
        this(member.getId(), member.getName(), member.getEmail());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
