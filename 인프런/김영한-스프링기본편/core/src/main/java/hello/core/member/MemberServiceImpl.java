package hello.core.member;

public class MemberServiceImpl implements MemberRepositroy{

    private final MemberRepositroy memberRepositroy = new MemoryMemberRepository();

    @Override
    public void save(Member member) {
        memberRepositroy.save(member);
    }

    @Override
    public Member findById(Long memberId) {
        return memberRepositroy.findById(memberId);
    }
}
