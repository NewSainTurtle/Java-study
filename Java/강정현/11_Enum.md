## Enum

**열거 타입, 열거형 (Enumeration Type)** 한정된 값만을 갖는 데이터 타입.

### Enum 정의

```java
enum 열거체_이름 {상수1, 상수2, ...}
```

```java
enum Rainbow {RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET}
```

- 열거체명은 클래스와 같이 첫 문자를 대문자로 하고, 나머지는 소문자로 구성한다.
- 관례적으로, 열거 상수(열거형의 값)는 모두 대문자로 작성한다.
- 열거 상수가 여러 단어로 구성될 경우, 단어 사이를 밑줄로 연결한다. 

**Enum 참조 방식**

```java
열거체_이름.상수_이름
```

```java
Rainbow.RED
```

- 열거형은 기본형이 아닌 참조형으로 분류되며, 열거 상숫값은 힙(heap) 영역에 저장된다.

<br/>

### Enum 장점

- 코드가 단순해지며, 가독성이 좋아진다.

- 문자열과 비교해, IDE 의 적극적인 지원을 받을 수 있다. : 자동완성, 오타검증, 텍스트 리팩토링 등.

- 허용 가능한 값들을 제한할 수 있다.

- 리팩토링 시 변경 범위가 최소화된다. 내용의 추가가 필요하더라도, Enum 코드 외에 수정할 필요가 없다.

<br/>

### Enum 상수값 정의 및 추가

열거형의 상숫값은 0부터 설정되며, 그 다음은 바로 앞의 상숫값보다 1만큼 증가되어 설정됨.

불규칙한 값을 상숫값으로 설정하고 싶으면 상수의 이름 옆에 괄호(`()`)를 추가하고, 그 안에 원하는 값을 명시함.

- 불규칙한 특정 값을 저장할 수 있는 인스턴스 변수와 생성자를 별도로 추가해야 함.

  ```java
  enum Rainbow {
    RED(3), ORANGE(10), YELLOW(21), GREEN(5), BLUE(1), INDIGO(-1), VIOLET(-11);
    
    private final int value;
    
    Rainbow(int value) {
      this.value = value;
    }
    
    public int getValue() {
      return value;
    }
  }
  ```

<br/>

### java.lang.Enum 클래스

Enum 클래스는 모든 자바 열거형의 공통된 조상 클래스. 모든 열거형은 Enum 클래스를 상속한다.

따라서 Enum 클래스는 상속이 불가함.

java.lang.Enum 에는 <u>String name, int ordinal</u> 필드가 존재, 각각 열거형의 상수 이름과 순서가 저장됨.

여러 메소드가 정의되어 있어 열거형에 유용한 기능을 제공함.

<br/>

### Enum 메소드 종류

#### 1. values()

해당 Enum 의 모든 상수를 저장한 배열을 생성하여 반환함.

```java
enum Rainbow {RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET}

Rainbow[] arr = Rainbow.values(); // 배열 생성
for (Rainbow rb : arr) {
  System.out.println(rb);
}
```

#### 2. valueOf()

전달된 문자열과 일치하는 해당 Enum 의 상수를 반환함.

```java
enum Rainbow {RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET}

Rainbow rb = Rainbow.valueOf("GREEN");
System.out.println(rb); //-> GREEN
```

#### 3. ordinal()

해당 열거체 상수가 열거체 정의에서 정의된 순서 (0부터 시작) 를 반환함.

이 때 반환되는 값은 **열거체 정의에서 해당 열거체 상수가 정의된 순서**이며, 상숫값 자체가 아님.

```java
enum Rainbow {RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET}

int idx = Rainbow.YELLOW.ordinal();
System.out.println(idx); //-> 2
```

##### 불규칙적인 상숫값인 경우

```java
enum Rainbow {
  RED(3), ORANGE(10), YELLOW(21), GREEN(5), BLUE(1), INDIGO(-1), VIOLET(-11);
  private final int value;
  Rainbow(int value) {
    this.value = value;
  }
  public int getValue() {
    return value;
  }
}

System.out.println(Rainbow.YELLOW.ordinal()); //-> 2
```

#### 4. name()

열거체가 가지고 있는 문자열을 반환함. 반환되는 문자열은 열거 타입을 정의할 때 사용한 상수 이름과 동일함.

```java
Rainbow rb = Rainbow.RED;

String rbName = rb.name();
System.out.println(rbName); //-> RED
```

#### 5. compareTo()

매개값으로 주어진 열거 객체를 비교해서, 순번 차이를 리턴.

- 열거 객체가 매개값의 열거 객체보다 순번이 빠르다 &rarr; 음수 리턴.
- 열거 객체가 매개값의 열거 객체보다 순번이 늦다 &rarr; 양수 리턴.

```java
Rainbow red = Rainbow.RED; // 0
Rainbow blue = Rainbow.BLUE; // 4

System.out.println(red.compareTo(blue)); //-> -4 
System.out.println(blue.compareTo(red)); //-> 4
```

<br/>

### EnumSet

java.util 패키지에서 제공하는 Enum 클래스와 함께 동작하는 특수한 Set 구현체.

#### EnumSet 특징

- Enum 값만 포함할 수 있고, 모든 값은 동일한 Enum 에 속해야 함.
- null 값을 추가하는 것을 허용하지 않으며, 시도하면 NullPointerException 발생.
- HashSet 보다 빠름.
- iterator 를 사용한 복제에서 fail-safe 하므로, 컬렉션을 반복하면서 컬렉션이 수정될 때 ConcurrentModificationException이 발생하지 않음.
- EnumSet 의 모든 메소드는 산술 비트 연산자를 이용하여 구현함.

<br/>

#### EnumSet 함수

**allOf()** : 모든 요소를 포함하는 EnumSet 생성.

```java
public enum Color {
    RED, YELLOW, GREEN, BLUE, BLACK, WHITE
}
```

```java
EnumSet<Color> set = EnumSet.allOf(Color.class);
```

**noneOf()** : 빈 Color 컬렉션을 갖는 EnumSet 생성.

```java
EnumSet<Color> set = EnumSet.noneOf(Color.class);
```

**of()** : 들어갈 요소를 직접 입력하여 EnumSet 생성.

```java
EnumSet<Color> set = EnumSet.of(Color.YELLOW, Color.BLUE);
```

**complementOf()** : 원하는 요소를 제거하고 EnumSet 생성.

```java
EnumSet<Color> set = EnumSet.complementOf(EnumSet.of(Color.BLACK, Color.BLUE));
set.forEach(System.out::println);
//-> RED YELLOW GREEN WHITE
```

**copyOf()** : 다른 EnumSet 의 모든 요소를 복사하여 EnumSet 생성.

```java
EnumSet.copyOf(EnumSet.of(Color.BLACK, Color.WHITE));
```

**add()** : EnumSet 에 요소 추가. 

**contains()** : 특정 요소가 EnumSet 에 있는지 확인. 

**remove()** : EnumSet 에서 특정 요소 제거.

```java
EnumSet<Color> set = EnumSet.of(Color.YELLOW, Color.BLUE);
set.add(Color.RED);
boolean isContain = set.contains(Color.RED);
set.remove(Color.RED);
```

<br/>

---

**Reference**

[Enum 클래스](http://www.tcpschool.com/java/java_api_enum)

[☕ 자바 Enum 열거형 타입 문법 & 응용 💯 정리](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%97%B4%EA%B1%B0%ED%98%95Enum-%ED%83%80%EC%9E%85-%EB%AC%B8%EB%B2%95-%ED%99%9C%EC%9A%A9-%EC%A0%95%EB%A6%AC)

[[Java] EnumSet](https://shplab.tistory.com/entry/Java-EnumSet)

[[Java] Enum, 자바의 열거타입을 알아보자](https://scshim.tistory.com/253)
