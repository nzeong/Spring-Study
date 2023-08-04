package hello.core.member;

public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository; // 인터페이스만 남김

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } // AppConfig에서 생성자로 주입

    @Override
    public void join(Member member) {
        memberRepository.save(member); // 다형성에 의해서 인터페이스가 아니라 오버라이드한 게 호출된다
    }
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
