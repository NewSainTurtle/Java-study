# 1. enum 정의하는 방법

### enum

- 열거형은 enum키워드를 사용하여 정의한다.
- 보통 클래스명과 같이 첫 글자를 대문자로 시작한다.
- { } 안에 열거값은 , 로 구분하며 상수와 같이 대문자를 사용한다.
- 열거형의 선언은 클래스 안에서도 선언할 수 있고, 밖에서도 선언할 수 있다.
- 열거타입 변수도 참조타입이기 때문에 null을 선언할 수 있다.

### enum 정의하는 방법

#### 1. 

```java
enum Week1 {
	SUN, MON, TUE, WED, THU, FRI, SAT
}
```

#### 2.

```java
enum Week2 {
	SUN("일"), MON("월"), TUE("화"), WED("수"), THU("목"), FRI("금"), SAT("토");
	final private String day;
	
	Week2(String day) {
		this.day = day;
	}
	
	String getDay() {
		return day;
	}
}
```

# 2. enum이 제공하는 메소드 (values()와 valueOf())

### 1. valueOf(String str)

- 문자열 str과 일치하는 열거값을 반환한다.
- 값이 없으면 예외 발생

### 2. values()

- eum의 요소들을 순서대로 enum타입의 배열로 반환한다.
- (ENUM$VALUES)의 카피임으로 자주 호출하지 않는 것이 좋다.

### 3. ordinal()

- 열거값의 순서를 반환한다.

### 4. name()

- 열거 객체가 가지고 있는 문자열을 리턴하는 메소드다.
- 리턴되는 문자열은 열거 타입을 정의할 때 사용한 상수 이름과 동일하다.

# 3. java.lang.Enum

- 모든 열거 타입은 컴파일 시에 Enum 클래스를 상속한다.
- 때문에 다중 상속을 지원하지 않는 java에서 enum 클래스는 상속을 받을 수 없다.
- java.lang.Enum에는 String name, int ordinal 필드가 존재한다.
- 여기에는 각각 열거 객체의 상수 이름과 순서가 저장된다. 또한 여러 메소드가 정의되어 있어서 열거 객체에 유용한 기능을 제공한다.

# 4. enumSet

- EnumSet은 **enum 클래스로 작동**하기 위해 특화된 **Set 컬렉션**(데이터를 중복 저장할 수 없고, 순서가 보장되지 않는 자료구조)이라고 할 수 있다.
- EnumSet은 **Set 인터페이스를 구현**하고, **AbstractSet을 상속**한다. 이러한 EnumSet의 내부는 비트 벡터로 구현되었다.

![enumSet](https://github.com/NewSainTurtle/CS-study/assets/63511273/105880fd-a17b-4f01-a7c9-7341fe6148b6)


- EnumSet은 추상 클래스이며, 인스턴스 생성을 위한 다양한 정적 팩토리 메서드가 정의되어있다. JDK에서는 **RegularEnumSet**, **JumboEnumSet** 2가지의 EnumSet 구현체를 제공한다.
- RegularEnumSet은 비트 벡터를를 표현하기 위해 단일 long 자료형을 사용한다. long의 각 비트는  enum 값을 나타낸다. 열거 형의 i번째 값은 i번째 비트에 저장되므로 값이 있는지 여부를 쉽게 알 수 있다. long이 64 비트의 데이터이기 때문에 RegularEnumSet은 64개의 원소를 저장할 수 있다.
- 반면에 JumboEnumSet은 long 요소의 배열을 비트 벡터로 사용한다. 이를 통해 64개 이상의 원소를 저장한다. RegularEnumSet과 비슷하게 작동 하지만, 저장된 배열 인덱스를 찾기 위해  몇 가지 추가 계산을 수행한다.

```java
if (universe.length <= 64)
    return new RegularEnumSet<>(elementType, universe);
else
    return new JumboEnumSet<>(elementType, universe);
```

- 이 때문에 EnumSet 팩터리 메서드는 enum의 원소 수에 따라 구현체를 다르게 선택한다.

### EnumSet 장점

- EnumSet의 모든 메서드(contains 등)는 산술 비트 연산을 사용하여 구현되므로 일반적인 연산이 매우 빠르게 계산된다.
- EnumSet은 HashSet 같은 다른 Set 구현체와 비교했을 때, 데이터가 예상 가능한 순서로 저장되어 있고, 각 계산을 하는데 하나의 비트만이 필요하므로 더 빠르다고 할 수 있다. 또한 HashSet 처럼 데이터를 저장할 버킷을 찾는 데 hashcode를 계산할 필요가 없다.
- EnumSet은 비트 벡터의 특성상 더 작은 메모리를 사용한다.

### EnumSet 고려사항

1. 열거형 값만 포함 할 수 있으며, 모든 값은 동일한 열거형이어야 한다.
2. null을 추가할 수 없다.
3. 스레드에 안전하지 않으므로, 필요할 경우 외부에서 동기화한다.
4. 복사본에 fail-safe iterator(장애 발생시 작업을 중단하지 않음) 를 사용하여 컬렉션을 순회할 때, 컬렉션이 수정되어도 ConcurrentModificationException이 발생하지 않는다.

### EnumSet 사용

```java
enum Week {
	SUN, MON, TUE, WED, THU, FRI, SAT
}
```

- EnumSet은 다양한 방식으로 만들 수 있다.

#### 1. allOf()

```java
EnumSet<Week> set = EnumSet.allOf(Week.class);
```

- 모든 요소를 포함하는 EnumSet을 만들 수 있다.

#### 2. noneOf()

```java
EnumSet<Week> set = EnumSet.noneOf(Week.class);
```

- 빈 Week 컬렉션을 갖는 EnumSet을 만들 수 있다.

#### 3. of()

```java
EnumSet<Week> set = EnumSet.of(Week.SUN, Week.SAT);
```

- 들어갈 요소를 직접 입력하여 EnumSet을 생성할 수 있다.

#### 4. complementOf()

```java
EnumSet<Week> set = EnumSet.complementOf(EnumSet.of(Week.MON,Week.WED));
```

- 원하는 요소를 제거하고 EnumSet을 생성할 수 있다.

#### 5. copyOf()

```java
EnumSet.copyOf(EnumSet.of(Week.SUN, Week.SAT));
```

- 다른 EnumSet의 모든 요소를 복사하여 EnumSet을 만들 수 있다.

#### 6. 그외 메소드

- add() : EnumSet에 요소를 추가할 수 있다.
- contains() : 특정 요소가 EnumSet에 포함되어 있는지 확인할 수 있다.
- remove() : EnumSet에서 특정 요소를 제거할 수 있다.

> #### 참고<br/>
> [[Java] Enum, 자바의 열거타입을 알아보자](https://scshim.tistory.com/253)
