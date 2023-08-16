## 0. enum

### enum이란

enum은 **열거형 상수**로 구성된 특별한 클래스이다. 

클래스처럼 변수와 메소드, 생성자를 가질 수 있다.

하지만 상속이나 인스턴스를 생성할 수는 없으며, enum 값은 상수로 public, static, final 속성을 가지고 있어 변경될 수 없다.

###  enum의 장점

1. 데이터 값의 의미를 명확히 알 수 있다. 
2. 컴파일 시 데이터 타입 및 유효성 체크를 할 수 있다. 

<br>

## 1. enum 정의하는 방법

열거형은 enum 키워드를 사용하여 정의하며, 열거형의 이름은 보통 클래스명과 같이 첫 글자를 대문자로 시작한다.

 { } 안에 열거값은 , 로 구분하며 상수와 같이 대문자를 사용한다.

enum의 정의에 나열된 값들은 0부터 순차적으로 1씩 증가하는 값을 갖는다.

열거형의 선언은 클래스 안에서도 선언할 수 있고, 클래스 밖에서도 선언할 수 있다.

```java
enum Day {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, STURDAY, SUNDAY;
}
```

클래스처럼 사용할 수도 있다.

```java
public enum Weeks {
  MONDAY("mon", 10),
  TUESDAY("tue", 20),
  WEDNESDAY("wed", 30),
  THURSDAY("thu", 40),
  FRIDAY("fri", 50), 
  STURDAY("sat", 60),
  SUNDAY("sun", 70);
  
  private final String name;
  private final int value;
  
  private Weeks(String name, int value) {
  	this.name = name;
    this.value = value;
  }
  
  public String getName() {
  	return this.name;
  }
  
  public int getValue() {
  	return this.value;
  }
}
```

<br>

## 2. java.lang.Enum

모든 열거 타입은 컴파일 시에 Enum 클래스를 상속한다. 

때문에 다중 상속을 지원하지 않는 java에서 enum 클래스는 상속을 받을 수 없다.

java.lang.Enum에는 String name, int ordinal 필드가 존재한다. 여기에는 각각 열거 객체의 상수 이름과 순서가 저장된다. 

또한 여러 메소드가 정의되어 있어서 열거 객체에 유용한 기능을 제공한다. 

<br>

## 3. enum이 제공하는 메소드

- **values( )** : enum 타입의 모든 값들을 배열로 만들어 리턴해주는 메소드.
	
	```java
	for (Weeks week : Weeks.values()) {
		System.out.print(week.getName()+" ");
	}
	
	// 출력결과
	// mon tue wed thu fri sat sun
	```

- **valueOf(String str)** : 문자열 str과 일치하는 열거값을 반환하는 메소드.

  ```java
  Weeks week = Weeks.valueOf("MONDAY");
  System.out.println(week.getName());
  
  // 출력결과
  // mon
  ```

- **name( )** : valueOf와 반대로 타입의 값이 가지고 있는 문자열을 리턴하는 메소드.

  - 여기서 리턴되는 문자열을 valueOf()의 인자로 입력하면 enum 값을 얻을 수 있다.

  ```java
  Weeks week = Weeks.MONDAY;
  System.out.println(week.name());
  
  // 출력결과
  // MONDAY
  ```

- **ordinal( )** : 열거값 중 현재 값이 몇 번째인지 순서를 반환하는 메소드. 

  ```java
  Weeks wed = Weeks.WEDNESDAY;
  System.out.println(wed.ordinal());
  
  // 출력결과
  // 2
  ```

- **compareTo( )** : 두 enum 값을 비교할수 있다.

  - 인자로 넘겨준 enum 값과 비교해서 전후로 몇 번째에 위치하는지를 나타낸다. 
  - 만약 enum 객체의 매개값의 enum객체보다 순번이 빠르다면 음수, 같다면 0, 늦다면 양수가 리턴된다. 

  ```java
  Weeks mon = Weeks.MONDAY;
  Weeks tue = Weeks.TUESDAY;
  Weeks wed = Weeks.WEDNESDAY;
  Weeks thu = Weeks.THURSDAY;
  
  System.out.print(mon.compareTo(Weeks.TUESDAY) + " ");
  System.out.print(tue.compareTo(Weeks.TUESDAY) + " ");
  System.out.print(wed.compareTo(Weeks.TUESDAY) + " ");
  System.out.print(thu.compareTo(Weeks.TUESDAY));
  
  // 출력결과
  // -1 0 1 2
  ```

<br>

## 4. EnumSet

*EnumSet* 은 java.util 패키지에서 제공하는 Enum 클래스와 함께 동작하는 구현체이다.

### 특징

- enum 값만 포함할 수 있고, 모든 값은 동일한 enum 에 속해야 한다.
- null 값을 추가하는 것을 허용하지 않으며, 시도하면 *NullPointerException* 이 발생한다.
- 스레드에 안전하지 않으므로, 필요한 경우 외부에서 동기화한다.
- 복사본에 fail-safe iterator(장애 발생시 작업을 중단하지 않음)를 사용하여 컬렉션을 순회할 때, 컬렉션이 수정되어도 *ConcurrentModificationException* 이 발생하지 않는다.

### 장점

- *EnumSet*의 모든 메서드(contains 등)는 산술 비트 연산을 사용하여 구현되므로 일반적인 연산이 매우 빠르게 계산된다. 
- *HashSet* 보다 훨씬 빠른 고성능 구현체
  - *Enumset*은 값을 예측 가능한 순서로 저장하여, 각각의 계산에 단 하나의 비트만 검사하면 되기 때문이다.
- *HashSet* 과는 달리 정확한 버킷을 찾기 위해 해쉬코드를 연산할 필요가 없다.

<br>

### EnumSet 사용하기

다음과 같은 Color 열거형이 있을 때, 사용 예시를 살펴보자.

```java
public enum Color {
    RED, YELLOW, GREEN, BLUE, BLACK, WHITE
}
```





#### 1. EnumSet 만드는 메소드

- **allOf( )** : 모든 요소를 포함하는 EnumSet을 만들 수 있다.

  ```java
  EnumSet<Color> set = EnumSet.allOf(Color.class);
  set.forEach(System.out::println);
  
  /* 출력결과
  RED
  YELLOW
  GREEN
  BLUE
  BLACK
  WHITE
  */
  ```

- **noneOf( )** : 빈 Color 컬렉션을 갖는 EnumSet을 만들 수 있다.

  ```java
  EnumSet<Color> set = EnumSet.noneOf(Color.class);
  ```

- **of( )** : 들어갈 요소를 직접 입력하여 EnumSet을 생성할 수 있다.

  ```java
  EnumSet<Color> set = EnumSet.of(Color.YELLOW, Color.BLUE);
  ```

- **complementOf( )** : 원하는 요소를 제거하고 EnumSet을 생성할 수 있다.

  ```java
  EnumSet<Color> set = EnumSet.complementOf(EnumSet.of(Color.BLACK,Color.BLUE));
  set.forEach(System.out::println);
  
  /* 출력결과
  RED
  YELLOW
  GREEN
  WHITE
  */
  ```

- **copyOf( )** : 다른 EnumSet의 모든 요소를 복사하여 EnumSet을 만들 수 있다.

  ```java
  EnumSet.copyOf(EnumSet.of(Color.BLACK, Color.WHITE));
  ```

<br>

#### 2. EnumSet를 다루는 다른 메소드

- **add( )** : EnumSet에 요소를 추가할 수 있다.

  ```java
  EnumSet<Color> set = EnumSet.of(Color.YELLOW, Color.BLUE);
  set.add(Color.RED);
  ```

- **contains( )**를 사용하면 특정 요소가 EnumSet에 포함되어 있는지 확인할 수 있다.

  ```java
  EnumSet<Color> set = EnumSet.of(Color.RED,Color.YELLOW, Color.BLUE);
  boolean isContain = set.contains(Color.RED);
  
  if(isContain) System.out.println("빨간색 포함");
  
  // 출력결과
  // 빨간색 포함
  ```

- **remove( )** : EnumSet에서 특정 요소를 제거할 수 있다.

  ```java
  set.remove(Color.RED);
  ```

<br>

---

**출처**

[자바 - enum 사용법 (열거형, 열거타입)](https://kadosholy.tistory.com/114)

[[Java]열거타입(Enum)사용법 및 예제](https://hbase.tistory.com/166)

[[Java] Enum, 자바의 열거타입을 알아보자](https://scshim.tistory.com/253)
