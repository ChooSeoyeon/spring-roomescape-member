package roomescape.service;

import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.domain.Member;
import roomescape.domain.MemberRepository;
import roomescape.exception.auth.InvalidTokenException;
import roomescape.service.dto.MemberOutput;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberOutput> findAllMember() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(MemberOutput::new)
                .toList();
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(InvalidTokenException::new);
    }
}
