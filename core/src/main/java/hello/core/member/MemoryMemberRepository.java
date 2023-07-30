package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member); // id를 store에 저장하기
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId); // id를 사용해서 가져오기
    }
}
