package roomescape.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import roomescape.domain.Member;

public class LoginCheckOutput {
    private final String name;

    @JsonCreator
    public LoginCheckOutput(String name) {
        this.name = name;
    }

    public LoginCheckOutput(Member member) {
        this(member.getName());
    }

    public String getName() {
        return name;
    }
}
