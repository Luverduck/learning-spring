package springcore.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import springcore.basic.member.MemberRepository;
import springcore.basic.member.MemoryMemberRepository;

@Configuration
@ComponentScan(
    // basePackages = "springcore.basic",
    // basePackageClasses = AutoAppConfig.class,
    // useDefaultFilters = false,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 빈 이름 : memoryMemberRepository
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
