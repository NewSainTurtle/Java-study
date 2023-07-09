[toc]

## JVM (Java Virtual Machine)

자바 프로그램 실행 환경을 만들어 주는 소프트웨어.

자바 코드를 컴파일하여 .class 바이트 코드로 만들면, 이 코드가 자바 가상 머신 환경에서 실행됨.



**바이트 코드 (Byte Code)**

JVM 이 이해할 수 있는 언어로 변환된 자바 소스 코드.

자바 바이트 코드는 JVM만 설치되어 있으면, 어떤 OS에서라도 실행될 수 있다. 



### Java는 OS에 종속적이지 않다: C vs. Java

![c언어](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbSyyF2%2FbtruTDnDSKz%2F73EXAY7aiTWRzHKlM2OFpK%2Fimg.png)

출처: https://coding-factory.tistory.com/827

- C언어로 작성된 Test.c 를 윈도우 컴파일러를 사용해서 컴파일하면 Test.exe가 생성됨. 이는 윈도우에서만 실행되는 실행 파일로, 리눅스 운영체제에서 실행할 수 없음.

  



![jvm](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F56cSc%2FbtruTEtjRXJ%2Fr1JNTkEuEeY8cSKtqcXCRK%2Fimg.png)

출처: https://coding-factory.tistory.com/827

- Java언어로 작성된 Test.java를 컴파일하면 Test.class 파일 (바이트 코드)이 생성됨.
- 생성된 바이트 코드는 각자의 플랫폼에 설치되어 있는 자바 가상 머신 (JVM)이 각 운영체제에 맞는 실행 파일로 바꿈.
- 각자의 플랫폼에 맞게 컴파일을 따로 할 필요가 없다.
- 하나의 바이트 코드로 JVM이 설치되어 있는 모든 플랫폼에서 동작이 가능하다.





## JAVA 실행 과정 : JVM 구성 요소

![컴파일 과정](https://velog.velcdn.com/images/minseojo/post/6d345e14-4ea1-4c5f-8903-852627514512/image.PNG)

1. 개발자가 **자바 소스코드 (.java)**를 작성한다.
2. 자바 컴파일러가 자바 소스코드 를 읽어 **바이트 코드 (.class)** 로 컴파일한다 - 명령어 javac. 
3. 컴파일된 바이트 코드 를 JVM의 **클래스 로더 (Class Loader)**에게 전달한다.
4. 클래스 로더는 동적 로딩 (Dynamic Loading)을 통해 필요한 클래스들을 로딩 및 링크하여 **런타임 데이터 영역 (Runtime Data Area)**, 즉 JVM의 메모리에 올린다. 
5. **실행 엔진 (Execution Engine)**은 JVM 메모리에 올라온 바이트 코드 들을 명령어 단위로 하나씩 가져와서 실행한다.



### 클래스 로더 (Class Loader) 

클래스들을 동적으로 메모리에 로딩하는 역할.

1. 로딩 (Loading)
   - .class 파일을 읽고, 그 내용에 따라 적절한 바이너리 데이터를 만들고 메서드 영역에 저장한다.
   - .class 파일이 JVM 스펙에 맞는지 확인하고, Java 버전을 확인한다.
2. 링크 (Linking)
   1. Verify : 읽은 클래스의 바이너리 데이터가 유효한 것인지 확인한다. 
   2. Prepare : 클래스의 static 변수와 기본값에 필요한 메모리 공간을 준비한다.
   3. Resolution : 심볼릭 메모리 레퍼런스를 메소드 영역에 있는 실제 레퍼런스로 교체한다. 
3. 초기화 (Initialization) 
   - 링크 단계의 Prepare 단계에서 확보한 메모리 영역에 클래스의 static 값들을 할당한다.
   - SuperClass 초기화와 해당 클래스의 초기화를 진행한다. 



### 실행 엔진

메모리에 적재된 클래스 (바이트 코드) 들을 기계어로 변경하여 명령어 단위로 실행한다.

실행 엔진이 바이트 코드를 명령어 단위로 읽어 수행하는데 크게 두 가지 방식이 사용된다.

**인터프리터 (Interpreter)**

- 바이트 코드를 한 라인씩 읽고 실행한다. 
- 반복 호출되는 메소드를 매번 인터프리팅하기 때문에 속도가 느리고, 비효율적임.

**JIT (Just In Time) 컴파일러**

- 인터프리터의 속도 개선.
- 실행 시점에 인터프리터 방식으로 기계어 코드를 생성하면서, 저장소에 따로 캐싱한다. 동일한 부분이 재호출되면, 캐싱해둔 코드를 불러온다. 



JVM은 기본적으로 인터프리터 방식을 사용하고, 내부적으로 특정 메서드가 얼마나 자주 수행되는지 체크하고, 일정 정도를 넘을 때만 JIT 컴파일러 방식을 사용한다. (참고: https://kotlinworld.com/307)





### 런타임 데이터 영역 (Runtime Data Area)

실제 클래스 파일이 적재되는 곳. JVM이 OS로부터 자바 프로그램 실행을 위한 데이터와 명령어를 저장하기 위해 할당받는 메모리 공간.

![runtime data area](https://blog.kakaocdn.net/dn/SDLq7/btriooQgkjm/rbKq3AxnpbkmUNBg69VNr1/img.png)

출처: https://8iggy.tistory.com/229

<details>
<summary>런타임 데이터 영역의 구성</summary>
<div markdown="1">
1. 메서드 영역 (Method Area)
   - 가장 먼저 데이터가 저장됨.
   - 클래스 로더에 의해 로드된 클래스, 메서드 정보와 클래스 변수 정보 저장.
   - 프로그램 시작부터 종료까지 메모리에 적재.
   - 명시적 null 선언 시 GC가 청소.
2. 힙 영역 (Heap)
   - 런타임 시 결정되는 참조 자료형이 저장됨.
   - new 연산자를 통해 생성된 객체가 저장되는 공간.
   - 객체가 더 이상 쓰이지 않거나, 명시적 null 선언 시 GC가 청소.
3. 스택 (Stack)
   - 컴파일 시 결정되는 기본 자료형 (& 참조 변수) 이 저장됨.
   - 메서드 호출 시 메모리에 FILO (First In Last Out) 로 삽입.
   - 메서드 종료 시 메모리에서 LIFO (Last In First Out )로 제거.
   - 메서드가 호출될 때마다 각각의 스택 프레임이 생성됨.
     - 각 스택 프레임은 하나의 메서드에 대한 정보를 저장한다.
   - {} 혹은 메서드가 종료될 때 삭제됨.
     - 메서드 종료 시 프레임 별로 삭제
   - 각 스레드 별로 생성.
4. PC 레지스터 
   - JVM이 수행할 명령어의 주소를 저장하는 공간.
   - 스레드가 시작될 때마다 생성됨.
   - 각 스레드 별로 생성.
5. 네이티브 메서드 스택
   - 바이트 코드가 아닌 기계어로 작성된 코드를 실행하는 공간.
   - 다른 언어(C, C++, ...) 로 작성된 코드를 수행하기 위함.
</div>
</details>



### Garbage Collector

(주로) Heap 메모리 영역에 생성된 객체들 중 참조가 끊긴 객체들을 찾아 제거한다.





## JDK vs. JRE

### JRE (Java Runtime Environment)

자바 가상 머신 (JVM), 자바 클래스 라이브러리 (Java Class Library), 자바 명령 (Java Command) 및 기타 인프라를 포함한, 컴파일된 Java 프로그램을 실행하는데 필요한 패키지.

### JDK (Java Development Kit)

Java를 사용하기 위해 필요한 모든 기능을 갖춘 Java용 SDK (Software Development Kit).

JRE 에 있는 모든 것 뿐만 아니라 컴파일러와 jdb, javadoc과 같은 도구를 포함한다.

### 









**Reference**

[Java의 컴파일 과정을 최대한 쉽게 이해해보자](https://ssocoit.tistory.com/270#2._%EB%B0%94%EC%9D%B4%ED%8A%B8%EC%BD%94%EB%93%9C_%EB%B3%80%ED%99%98)

[[Java]자바 가상머신 JVM(Java Virtual Machine) 총정리](https://coding-factory.tistory.com/827)

[Java 런타임 데이터 영역](https://8iggy.tistory.com/229)

[[Java] JDK, JRE 차이점 (JDK란? JRE란?)](https://developerntraveler.tistory.com/49)









