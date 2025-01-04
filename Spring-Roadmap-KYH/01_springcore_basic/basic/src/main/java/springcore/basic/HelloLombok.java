package springcore.basic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        // 클래스의 인스턴스 생성
        HelloLombok helloLombok = new HelloLombok();
        // @Getter / @Setter를 통해 getter / setter 메소드 자동 생성
        helloLombok.setName("Hello");
        helloLombok.setAge(25);
        System.out.println("name = " + helloLombok.getName());
        System.out.println("age = " + helloLombok.getAge());
        // @ToString을 통해 toString 메소드 자동 생성
        System.out.println("helloLombok = " + helloLombok);
    }

}
