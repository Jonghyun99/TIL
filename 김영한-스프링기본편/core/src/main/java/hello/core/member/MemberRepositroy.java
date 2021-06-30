package hello.core.member;

public interface MemberRepositroy {

    void save(Member member);

    Member findById(Long memberId);
}
