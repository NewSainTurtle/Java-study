# Enum

열거형 enum이란, 관련이 있는 상수들의 집합이다.

``` java
public enum  Rank {
	THREE(3, 4_000),
	FOUR(4, 10_000),
	FIVE(5, 30_000);
    
	private final int match;
	private final int money;
	private int count;
    
	Rank(int match, int money) {
		this.match = match;
		this.money = money;
	}
	
	public void plusCount() {
    		this.count++;
    }
}
```

### enum의 특징 
- 클래스를 상수처럼 사용할 수 있다.
  - 열거타입의 생성자는 `private`으로 되어있으며, `public`으로 변경하는 경우 컴파일 에러가 발생한다.
  - 즉, 다른 클래스나 인터페이스에서의 상수 선언이 클래스 로드 시점에서 생성되는 것처럼 `Enum` 또한 생성자가 존재하지만 클래스가 로드되는 시점에서 생성되기 때문에 임의로 생성하여 사용할 수 없다.
  - 이를 사용하고자 한다면 `Rank.FIVE` 와 같은 형태로 상수처럼 사용하면 된다.
- Enum 클래스를 구현하는 경우 상수 값과 같이 유일하게 하나의 인스턴스가 생성되어 사용된다.
  - Enum 클래스에서 선언한 상수들은 클래스가 로드될 때 하나의 인스턴스로 생성되어 **싱글톤** 형태로 어플리케이션 전체에서 사용된다. 
  - 싱글톤으로 사용되기 때문에 각각의 Enum 인스턴스에 변수를 추가하여 사용하는 것은 멀티 쓰레드 환경에서 위험할 수 있다. 
    - 위 `Rank` 예제에서 각각의 인스턴스에 `count` 라는 변수가 추가되어 있는데 외부에서 각 등수에 맞게 `plusCount()` 를 호출 할 수 있다. 하지만 멀티 쓰레드 환경에서는 각 인스턴스의 `count`가 공유되고 있기 때문에 조심해야 한다.
- 클래스와 같은 문법 체계를 따른다.
- enum 값은 상수로 public, static, final 속성을 가지고 있어 변경될 수 없다.
- 상속을 지원하지 않는다.
  - 모든 enum들은 내부적으로 `java.lang.enum` 클래스에 의해 상속된다.
  - 자바는 다중 상속을 지원하지 않기 때문에, enum은 다른 클래스를 상속받을 수 없다.
  - 기존에 상속받고 있는 클래스가 존재하기 때문에 다중 상속은 지원하지 않지만, 다양한 인터페이스들은 구현할 수 있다.

### enum의 장점
- 데이터 값의 의미를 명확히 알 수 있다.
- 컴파일 시 데이터 타입 및 유효성 체크를 할 수 있다.


### enum 정의하는 방법

``` java
public enum 열거타입이름 { ... }
```

- `enum` 키워드를 사용하여 정의
- 열거형의 이름은 첫글자를 대문자로 시작
- `{ }` 안에 열거값은 쉼표`,`로 구분하며, 상수와 같이 대문자를 사용
- 열거형은 클래스 안에서도, 밖에서도 선언가능

``` java
public enum Color { RED, YELLOW, GREEN, BLUE, BLACK, WHITE }
```

### java.lang.Enum

- 모든 열거형은 컴파일 시에 Enum 클래스를 상속한다. 
  - 자바는 다중 상속을 지원하지 않기 때문에, enum은 다른 클래스를 상속받을 수 없다.
- `java.lang.Enum`에는 String name, int ordinal 필드가 존재한다. 
  - 여기에는 각각 열거 객체의 상수 이름과 순서가 저장된다. 
- 여러 메소드가 정의되어 있어서 열거 객체에 유용한 기능을 제공한다. 

### enum이 제공하는 메소드

| static 여부  | 메소드                                  | 설명                                                           |
|------------|--------------------------------------|--------------------------------------------------------------|
| static     | valueOf(String arg)                  | String arg과 일치하는 열거값을 반환함.<br>내부적으로 자기 자신 class를 가져옴.<br>⚠️ 값이 없으면 예외 발생 |
| static     | valueOf(Class<T> class, String arg)  | 넘겨받은 class에서 String arg를 찾아, 열거값을 반환함.                       |
| static     |  values()                            | enum의 요소들을 순서대로 enum타입의 배열로 리턴.                              |
| non-static | name()                               | 호출된 값의 이름을 String으로 리턴.                                      |
| non-static | ordinal()                            | 해당 값이 enum에 정의된 순서를 리턴.                                      |
| non-static | compareTo(E o)                       | enum과 지정된 객체의 순서를 비교. <br>지정된 객체보다 작은 경우 음의 정수, 동일하면 0, 크면 양의 정수 리턴. |
| non-static | equals(Object other) 	               | 지정된 객체가 enum 정수와 같은 경우, true를 리턴.                            |


1. **values()**
   - Enum 클래스가 가지고 있는 모든 상수 값을 배열의 형태로 반환한다.
     - 참고로 단순히 String의 형태로 반환하는 것이 아니라, 인스턴스를 반환하는 것이다.
     - 즉, Enum 클래스가 가지고 있는 모든 인스턴스를 배열에 담아 반환하는 것이다.
     - 참고로 `toString()` 메서드는 상수의 이름을 리턴하도록 구현되어 있다.

``` java
public static void main(String[] args) {
	Rank[] values = Rank.values();
	for(int i = 0; i< values.length; i++) {
		System.out.println(values[i]);
	}
}
```

> THREE, FOUR, FIVE

2. **valueOf()**
   - 인자로 들어온 String과 일치하는 상수 인스턴스가 존재하면 그 인스턴스를 반환한다.
     - 이것 역시 단순히 문자열을 반환하는 것이 아니라, 인자로 들어온 문자열과 일치하는 인스턴스를 반환한다.

``` java
public static void main(String[] args) {
	System.out.println(Rank.valueOf("THREE"));
}
```

> THREE

3. **ordinal()**
   - Enum 클래스 내부에 있는 상수들의 Index를 리턴하는 메소드이다. 
   - 배열과 마찬가지로 0부터 인덱스가 시작한다.

``` java
public static void main(String[] args) {
	Rank[] values = Rank.values();
	for(int i = 0; i< values.length; i++) {
		System.out.println(values[i] + " 인덱스는 : " + values[i].ordinal());
	}
}
```

> THREE 인덱스는 : 0 <br>
>     FOUR 인덱스는 : 1 <br>
>     FIVE 인덱스는 : 2 


## EnumSet

- enum 클래스로 작동하기 위해 특화된 Set 컬렉션이다. 
  - Set: 데이터를 중복 저장할 수 없고, 순서가 보장되지 않는 자료구조
- EnumSet은 **Set 인터페이스를 구현**하고, **AbstractSet을 상속**한다. 
- 이러한 EnumSet의 내부는 비트 벡터로 구현되었다.


### EnumSet 특징
- 열거형 값만 포함할 수 있으며, 모든 값은 동일한 열거형이어야 한다.
- `null`을 추가할 수 없다.
- 쓰레드에 안전하지 않으므로, 필요할 경우 외부에서 동기화해야한다.

**EnumSet은 추상 클래스**이며, 인스턴스 생성을 위한 다양한 정적 팩토리 메서드가 정의되어있다. 

> JDK에서는 **RegularEnumSet**, **JumboEnumSet** 2가지의 EnumSet 구현체를 제공한다.>  

`RegularEnumSet`은 비트 벡터를를 표현하기 위해 단일 long 자료형을 사용한다. 
- long의 각 비트는  enum 값을 나타낸다. 
- 열거 형의 i번째 값은 i번째 비트에 저장되므로 값이 있는지 여부를 쉽게 알 수 있다.
- long이 64 비트의 데이터이기 때문에 `RegularEnumSet`은 64개의 원소를 저장할 수 있다.
   

반면에 `JumboEnumSet`은 long 요소의 배열을 비트 벡터로 사용한다. 
- 이를 통해 64개 이상의 원소를 저장한다. 
- `RegularEnumSet`과 비슷하게 동작하지만, 저장된 배열 인덱스를 찾기 위해 추가 계산을 수행한다. 
- 이 때문에 EnumSet 팩터리 메서드는 enum의 원소 수에 따라 구현체를 다르게 선택한다.


### EnumSet의 장점
- `EnumSet`의 모든 메서드는 산술 비트 연산을 사용해 구현되기 때문에, 일반적인 연산 계산이 매우 빠르다. 
- 비트 벡터의 특성상 더 작은 메모리를 사용한다.
- `HashSet` 같은 다른 Set 구현체와 비교했을 때, 
  - 데이터가 예상 가능한 순서로 저장되어 있다.
  - 각 계산을 하는데 하나의 비트만이 필요하므로 더 빠르다고 할 수 있다. 
  - `HashSet`처럼 데이터를 저장할 버킷을 찾는 데 hashcode를 계산할 필요가 없다. 


### EnumSet이 제공하는 메소드

| 메소드            | 설명                                    |
|----------------|---------------------------------------|
| allOf()        | 모든 요소를 포함하는 EnumSet을 만든다.             |
| noneOf()       | 빈 열거형 컬렉션을 갖는 EnumSet을 만든다.           |
| of()           | 들어갈 요소를 직접 입력하여 EnumSet을 생성한다.        |
| complementOf() | 원하는 요소를 제거하고 EnumSet을 생성한다.           |
| copyOf()       | 다른 EnumSet의 모든 요소를 복사하여 EnumSet을 만든다. |
| add()          | EnumSet에 요소를 추가한다.                    |
| contains()     | EnumSet에 포함되어 있는지 확인한다.               |
| remove()       | EnumSet에서 특정 요소를 제거한다.                |

``` java
public enum Color { RED, YELLOW, GREEN, BLUE }
```

1. **allOf()**

모든 요소를 포함하는 EnumSet을 만든다.

``` java
EnumSet<Color> set = EnumSet.allOf(Color.class);
set.forEach(System.out::println);
```

> RED <br>
> YELLOW <br>
> GREEN <br>
> BLUE 

2. **of()**

들어갈 요소를 직접 입력하여 EnumSet을 생성한다.

``` java
EnumSet<Color> set = EnumSet.of(Color.YELLOW, Color.BLUE);
```

3. **complementOf()**

원하는 요소를 제거하고 EnumSet을 생성한다.

``` java
EnumSet<Color> set = EnumSet.complementOf(EnumSet.of(Color.BLUE, Color.RED));
set.forEach(System.out::println);
```

> YELLOW <br>
> GREEN 

4. **add()**

EnumSet에 요소를 추가한다.

``` java
EnumSet<Color> set = EnumSet.of(Color.YELLOW, Color.BLUE);
set.add(Color.RED);
```


<br>

> 참고 <br>
> [1] https://limkydev.tistory.com/66 <br>
> [2] [https://velog.io/@kyle](https://velog.io/@kyle/%EC%9E%90%EB%B0%94-Enum-%EA%B8%B0%EB%B3%B8-%EB%B0%8F-%ED%99%9C%EC%9A%A9) <br>
> [3] https://scshim.tistory.com/253 <br>

<br>
