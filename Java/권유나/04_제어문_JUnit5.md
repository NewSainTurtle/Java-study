# JUnit
**JUnit이란?**

대부분의 언어는 단위 테스트를 지원하며 이를 xUnit이라고 한다. JUnit은 Java 단위테스팅 프레임워크이다. 

JUnit은 메소드 값과 결과가 동일한지, 해당 메소드가 기대한 동작을 하는지, 예외상황에서는 예외를 정확하게 반환하는지 등에 대한 테스트를 지원한다.

`@(어노테이션)`을 이용해서 손쉽게 만들 수 있고 AssertEquals 등을 활용해 코드로써 테스트가 가능한다.

## JUnit5
JUnit5는 Java 8 이상 지원한다. 

JUnit5는 이전 버전과 다르게 3개의 서브 프로젝트 모듈로 이루어져 있다.

> JUnit5 = JUnit Platform + JUnit Jupiter + JUnit Vintage

![JUnit5](https://github.com/NewSainTurtle/CS-study/assets/26339069/6056ada3-7992-474b-9c0c-e450a5daf18b)


- **JUnit Platform**: JVM에서 테스트 프레임워크를 시작하기 위한 기반을 제공, 테스트를 실행해주는 런처와 TestEngine API를 제공
- **JUnit Jupiter**: TestEngine API 구현체로 JUnit5에서 제공
- **JUnit Vintage**: TestEngine API 구현체로 JUnit3, 4에서 제공

JUnit5에서 JUnit Vintage모듈을 포함하고 있기 때문에 JUnit3, 4 문법을 사용할 수 있다. 그러나 완벽하게 지원하는 것이 아니기 때문에 사용한다면 추가로 작업이 필요하다.

## @ Annotation

> @Test

테스트 메소드를 나타내는 어노테이션이다.

``` java
@Test
public void test(){}
```

> @DisplayName

테스트 클래스나 테스트 메소드에 이름을 붙여줄 때 사용하다.

``` java
@Test
@DisplayName("~~ 테스트")
public void test(){}
```


> @DisplayNameGeneration

`@DisplayName`처럼 별도의 이름을 주는 것이 아닌 코딩한 클래스, 메소드 이름을 이용해 변형시키는 어노테이션이다.

DisplayNameGeneration에 사용하는 파라미터가 DisplayNameGenerator클래스에 내부 클래스로 정의되어있다.
| 클래스명 | 설명 | 
| ---| ---|
| Standard | (기본값) 기존 클래스, 메소드 명을 사용함 |
|Simple | 괄호를 제외시킴|
|ReplaceUnderscores| _(underscore)를 공백으로 바꿈|
|IndicativeSentences | 클래스명 + 구분자(“,”) + 메소드명으로 바꿈 <br>(@IndicativeSentencesGeneration 를 이용해서 구분자를 변경할 수 있음) |

``` java
class Test {
	@DisplayNameGeneration(DisplayNameGenerator. IndicativeSentences.class)
	class IndicativeSentencesTest {
		@Test
		void test() {
		}
	}

@IndicativeSentencesGeneration(separator = " # ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
	class IndicativeSentencesGenerationTest {
		@Test
		void test() {
		}
	}
}
```

properties를 통해 @DisplayNameGeneration을 기본값으로 설정할 수 있다.

<br>

💡만약, @DisplayName, @DisplayNameGeneration, default 값 설정이 다 되어있으면 뭐가 먼저 적용될까? 적용 순서는 다음과 같다.<br>
1. @DisplayName<br>
2. @DisplayNameGeneration<br>
3. properties에 명시한 default 값<br>
4. 1, 2, 3 중 아무것도 없으면 DisplayNameGenerator.Standard.class<br>

<br>

> @BeforeAll

테스트 시작 전에 실행되는 메소드에 사용하며, 딱 한번만 실행된다.

static 메소드여야 한다. (예외인 경우가 있다.)

``` java
@Test 
@BeforeAll 
public void test(){ 
	log.info("~~에 대한 전체 테스트를 시작합니다."); 
}
```

> @BeforeEach

각 테스트 메소드 시작 전에 실행되어야 하는 메소드를 명시한다.

테스트하기 전 필요한 목업 데이터를 미리 세팅하기 위해 주로 사용한다.

``` java
@Test 
@BeforeEach 
public void test(){ 
	this.testObject = new TestObject(); 
}
```

> @AfterAll

테스트 종료 후에 실행되는 메소드에 사용하며, 딱 한번만 실행된다.

static 메소드여야 한다. (예외인 경우가 있다.)

``` java
@Test 
@AfterAll 
public void test(){ 
	log.info("~~에 대한 전체 테스트를 완료하였습니다."); 
}
```

> @AfterEach

각 테스트 메소드 종료 후에 실행되어야 하는 메소드를 명시한다.

``` java
@Test 
@AfterEach 
public void test(){ 
	this.testObject = null; 
}
```

> @Disabled

실행되지 않아야 하는 테스트 메소드에 사용한다.

``` java
@Test
@Disabled
public void disabled_test(){
	System.out.println("해당 메소드는 실행되면 안됩니다."); 
}
```


<details>
  <summary>더보기</summary>
  </br>
  
<div markdown="1">

| 어노테이션    | 설명            |  
| --------- | --------------- | 
|	@ParameterizedTest	|	메서드가 매개 변수가 있는 테스트임을 나타냄	|
|	@RepeatedTest	|	반복 테스트를 위한 메소드임을 나타냄	|
|	@TestFactory	|	동적 테스트를 위한 테스트 팩토리 메소드를 나타냄	|
|	@TestTemplate	|	여러번 호출되도록 설계된 테스트 케이스의 템플릿임을 나타냄 	|
|	@TestMethodOrder	|	테스트 메소드 실생 순서를 구성하는데 사용됨	|
|	@TestInstance	|	테스트 라이프 사이플을 구성하는데 사용됨	|
|	@Nested	|	non-static 중첩 클래스임을 나타냄 	|
|	@Tag	|	새로운 태그를 선언할 때 사용됨 	|
|	@Timeout	|	주어진 시간안에 실행을 못할 경우 실패하도록 하는데 사용됨	|
|	@TestMethodOrder |	테스트의 실행 순서를 정해야 할 때 사용됨 |
|	… |	… |

</div>
</details>

## Assertions, Assumptions
### Assertions

주장이라는 뜻으로 테스트가 원하는 결과를 제대로 반환하는지 확인할 때 사용하는 메소드이다.

| 메소드명    | 설명            |  
| --------- | --------------- | 
|	fail	|	무조건 실패 (레거시에 사용하면 좋다.)	|
|	assertTrue	|	조건이 성공이면 True	|
|	assertFalse	|	조건이 실패면 True	|
|	assertNull	|	조건이 Null이면 True	|
|	aseertNotNull	|	조건이 Not Null이면 True	|
|	assertEquals(expected, actual)	|	expected와 actual이 동일하면 True	|
|	assertArrayEquals	|	두 Array가 동일하면 True	|
|	assertIterableEquals	|	두 Iterable이 동일하면 True	|
|	assertLinesMatch	|	두 Stream이 동일하면 True	|
|	assertNotEquals(expected, actual)	|	expected와 actual이 다르면 True	|
|	assertSame	|	동일한 Object면 True	|
|	assertNotSame	|	다른 Object면 True	|
|	assertAll	|	여러 Assertion이 True면 True	|
|	assertThrows	|	예상한 에러가 발생하면 True	|
|	assertDoesNotThrow	|	에러가 발생하지 않으면 True	|
|	assertTimeout	|	테스트가 지정한 시간보다 오래 걸리지 않으면 True<br>지정한 시간보다 오래 걸려도 테스트가 끝날 때까지 대기	|
|	assertTimeoutPreemptively	|	테스트가 지정한 시간보다 오래 걸리지 않으면 True<br>지정한 시간보다 오래 걸린 경우 바로 테스트 종료	|

### Assumptions

추정이라는 뜻으로 메소드별 조건을 만족할 경우 진행시키고 아닌 경우 스킵하는 메소드이다.

💡 테스트 if문이라 생각하자!

| 메소드명    | 설명            |  
| --------- | --------------- | 
|	assumeTrue	|	테스트가 실패하면 에러 발생	|
|	assumeFalse	|	테스트가 성공하면 에러 발생	|
|	assumingThat(boolean, executable)	|	첫 번째 인자가 True면 두 번째 인자로 들어온 함수 실행<br>첫 번째 인자 값이 false 인 경우에도 테스트를 스킵하지 않고 다음 코드 진행	|

### Assertions vs. Assumptions

**Assertions** : 개발자가 테스트하고 싶은 인자값을 넣었을 때 예상한 결과가 나오는지 테스트 해볼 경우 사용

 예시) ”A”를 넣으면 “B”가 나온다

**Assumptions** : 개발자가 인자값을 정확히 모를 때 if 와 같은 용도로 사용

예시)  현재 테스트 환경이 “DEV”라면 테스트를 진행해라

## Hamcrest, AssertJ
assertions, assumptions 를 좀 더 가독성있고 편하게 쓸 수 있도록 도와준다.

spring-boot-starter-test 라이브러리에 포함되어 있다.

### Hamcrest

JUnit4 라이브러리에 포함되어 있었는데, JUnit5로 오면서 빠지게된 라이브러리이다.

| 메소드명    | 설명            |  
| --------- | --------------- | 
|	assertThat(T actual, Matcher<? super T> matcher)	|	비교 메소드	|

**Mather란?** assertThat 메소드의 첫 번째 인자로 들어온 값을 검증하기 위한 클래스이다. [참고🔗](https://hamcrest.org/JavaHamcrest/javadoc/2.2/)

### AssertJ

| 메소드명    | 설명            |  
| --------- | --------------- | 
|	assertThat(T actual)	|	테스트 시작 메소드	|

AssertJ의 assertions은 `assertThat(“actual”).isEqualTo(“actual")`와 같이 메소드 체이닝 방식을 사용한다. [참고1🔗](https://joel-costigliola.github.io/assertj/assertj-core.html) [참고2🔗](http://joel-costigliola.github.io/assertj/core-8/api/index.html)

### Hamcrest vs. AssertJ

**Hamcrest** 
- assertions가 메소드 안에 인자로 있음
- Matcher 명을 외워서 사용하거나 필요한 Matcher가 있으면 구글링을 해야 하는 불편함

**AssertJ** 
- 단순히 테스트할 값만 받고 메소드 체이닝으로 assertions를 구현함
- assertions이 메소드 체이닝 형식으로 구현되어 있어 굳이 메소드의 풀네임을 알필요가 없음

  <br>
  
> 참고 <br>
> [1]  https://codinghack.tistory.com/88 <br>
> [2] https://steady-coding.tistory.com/349 <br>
> [3] https://effortguy.tistory.com/112 <br>
> [4] https://math-coding.tistory.com/158 <br>
