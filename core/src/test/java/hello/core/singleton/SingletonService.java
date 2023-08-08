package hello.core.singleton;

public class SingletonService {

    // 1. static 영역에 객체 1개 딱 생성해둠
    private static final SingletonService instance = new SingletonService();

    // 2. 그 객체 인스턴스 필요하면 static 매서드 통해서만 조회하도록 허용한다!
    public static SingletonService getInstance(){
        return instance;
    }

    // 3. 생성자를 private로 선언해서 외부에서 new 키워드 이용한 객체 생성 막음
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}

