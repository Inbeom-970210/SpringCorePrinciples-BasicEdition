# 스프링 핵심 원리 - 기본편

**1. @ComponentScan(excludeFilters) 관련 이슈**

- 문제:
  @ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))를 사용했지만
  @configuration을 사용한 클래스가 빈으로 등록되면서 memberRepository타입의 빈이 2개 생성되어 의존성 주입 과정에서 충돌 발생.
- 원인: @SpringBootApplication에 @ComponentScan이 포함되어있고, 스프링의 @ComponentScan이 중복 적용된 경우에는 excludeFilters가 적용되지 않음.
- 해결: @Primary를 사용하여 동일한 타입의 빈에 우선순위를 부여. 이를 통해서 의존성 주입에서 충돌 문제를 해결하였습니다.

**2. @Autowired 필드명 매칭 관련 이슈**

- 문제:
  @Autowired 필드명 매칭을 통해서 충돌 문제를 해결하려고 했지만 실패함.
- 원인: 스프링 부트 3.2부터 매개변수의 이름을 인식하지 못하는 문제가 있어 @RequestParam, @PathVariable, @Autowired 등에서 문제가 발생함.
- 해결: @Autowired 필드명 매칭 대신 @Primary를 사용하여 동일한 타입의 빈에 우선순위를 부여. 이를 통해서 의존성 주입에서 충돌 문제를 해결하였습니다.

**3. AnnotationConfigApplicationContext() 관련 의문**

- 문제:
  ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);를 했더니
  DiscountService 클래스에서 @Component를 사용하지 않고도 @Autowired를 사용할 수 있음.
- 원인: new AnnotationConfigApplicationContext()의 생성자에 매개변수로 클래스 정보를 넘겨주면 해당 클래스도 스프링 빈으로 등록된다.
- 해결: 따라서 DiscountService에서 @Autowired를 사용할 수 있다.