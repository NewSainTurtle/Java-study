
# 1. JVM이란 무엇인가?

- java를 실행하기 위한 가상 기계
- java는 OS에 종속적이지 않다 → OS에 종속받지 않고 실행되기 위해서는 OS 위에서 java를 실행시킬 무언가가 필요하다. 이것이 JVM
- 즉, OS에 종속받지 않고 CPU가 java를 인식, 실행할 수 있게 하는 가상 컴퓨터이다.
  
> 보통 소스코드(원시코드)는 CPU가 인식을 하지 못하므로, 기계어로 컴파일 해준다.

> 하지만 java에서는 JVM이라는 가상머신을 거쳐서 OS에 도달하기 때문에 OS가 인식할 수 있는 기계어로 바로 컴파일 되는게 아니라 JVM이 인식할 수 있는 Java bytecode(*.class)로 변환된다.
> 변환된 Java bytecode는 기계어가 아니게 된다. 그래서 OS에서 바로 실행을 하지 못한다.

> JVM은 OS가 bytecode를 이해할 수 있도록 해석해주는 역할을 한다.

> 그래서 Bytecode는 OS 상관없이 실행될 수 있는 것이다.

> OS에 종속적이지 않고, OS에 맞게 해석해주는 JVM만 있다면, 하나의 Java 파일로 어느 디바이스에서든 JVM위에서 실행할 수 있다.

# 2. 컴파일 하는 방법

- 자바에서 IDE를 활용하여 컴파일하는 방법 뿐아니라, jdk의 javac 컴파일러를 활용하여 cmd창으로 직접 컴파일 할 수 있다.
- Java compiler는 JDK를 설치하면 bin에 존재하는 javac.exe를 말한다.
(즉, JDK에 Java compiler가 포함되어 있다는 소리)
- javac 명령어를 통해 *.java를 *.class로 컴파일 할 수 있다.

> java와 javac 설치경로 확인
> 
> C:>Program files>Java>bin(binary)>java.exe/javac.exe

### 컴파일 순서
1. 컴파일 하고자 하는 *.java 파일 생성
2. cmd 명령 프롬프트창에서 1번 파일위치로 이동
3. javac [파일명.java](http://파일명.java) 명령어를 입력하여 컴파일 진행
```
Usage: javac <options> <source files>
```
※ 만약 javac가 실행되지 않으면 자바 환경변수 세팅 문제가 있을 수 있다.

# 3. 실행하는 방법


### java와 javac 설치경로 확인

- C:>Program files>Java>bin(binary)>java.exe/javac.exe

### 환경변수

- JAVA 명령을 내렸을때, 이 명령이 어디서든 실행될 수 있는 이유는 path라고 불리는 환경변수를 설정 해주었기 때문이다.
- 내 컴퓨터 > 속성 > 고급 시스템 설정 > 환경 변수
- 위쪽 사용자 변수 (내 컴퓨터에만 설정된 것.)
- 아래쪽 시스템 변수 (이 시스템을 사용하는 모든 사람에게 적용되는 설정)

### 실행하기

- javac 명령어를 사용하기 위해선 javac의 경로가 path에 등록되어 있는 상태여야한다.
- javac로 java파일을 컴파일 하여 *.class 파일을 만든다. (JVM을 활용하여 OS가 해석할수 있는 상태가 된 것)
- java 명령어를 사용하여 실행

```sql
java 파일이름(.class를 붙이지 않고 실행)
```

#### 실행 순서

1. 자바는 파일에서 해당 이름의 class를 찾는다.
2. class 내부의 main 메서드를 찾는다.
3. main 내부의 코드를 실행한다.

# 4. 바이트 코드란 무엇인가

- java로 이루어진 코드 파일을 JVM이 이해할 수 있게 변환된 소스 코드이다.
- *.java 파일을 자바 컴파일러에 의해 *.class로 변환된 것을 말한다.
- 자바 컴파일러에 의해서 변환되는 코드 명령어 크기가 1바이트이기 떄문에 자바 바이트 코드라고 부른다.
- JVM에 의해 OS에 종속되지 않고 실행할 수 있다.

# 5. JIT 컴파일러란 무엇이며 어떻게 동작하는지

- JIT 컴파일러는 bytecode를 nativecode로 변환한다.
- 변환 하는 과정에서 비용이 발생한다.
- 이런 비용이 발생하는 문제 때문에 JVM은 모든 코드를 JIT 방식으로 변환하지 않고, 인터프리터 방식을 사용하다가 자주 사용하는 코드만 캐싱하여 사용한다.
- JVM은 내부적으로 어떤 메서드가 자주 실행 되는지 확인하고, HotSpot이라고 판단되면 컴파일을 수행해 놓는다.

### 컴퓨터 프로그래밍을 실행하는 방식
1. 인터프리터 방식
    - 프로그램을 실행할 때마다, 컴퓨터가 알아들을 수 있는 언어로 한줄씩 한줄씩 변환하는 작업
    - 간편하지만 속도가 느리다.
    - 초기에 JVM은 인터프리터 방식만을 사용하여 한줄한줄 읽어 속도가 느린것이 단점이 었지만, JIT컴파일러 방식을 도입해 속도를 보완했다.
2. static 컴파일 방식
    - 실행하기전에 미리 기계어로 변역하는 작업
    - 따라서 변환하는 작업은 딱 한번만 수행한다.
    - JIT 컴파일러가 이에 해당한다.

- JIT 컴파일러
   - Just-In-Time의 약자이다. 동적 변환(Dynamic translation) 이다.
   - 자바 뿐아니라 .NET 등 여러 언어에서 사용
   - 명칭은 컴파일러지만 실행시에 적용되는 기술
   - 지속적으로 인터프리터 방식을 사용하다가, 인터프리터가 자주 사용하는 코드의 정보를 캐시에 저장하고, 미리 꺼내서 바로 실행하는 방식
 
![JIT컴파일러](https://github.com/NewSainTurtle/CS-study/assets/63511273/73f55daf-1682-40d2-a45c-1aa18bea7589)

- Javac 컴파일러는 어떤 OS에서든 실행할 수 있는 Bytecode파일을 만들고
- 컴퓨터가 이해할 수 있는 기계어로 다시 변환을 하는데,
- 이 역할을 JVM안의 JIT컴파일러가 수행한다.

# 6. JVM 구성요소

### 1. Class Loader (클래스 로더)
  - 자바는 동적으로 클래스를 읽어오므로, 프로그램이 실행 중인 런타임에 모든 코드가 자바 가상 머신과 연결된다.
  - 이렇게 동적으로 클래스를 읽어 연결해주는 역할이 클래스 로더이다.
  - 클래스 로더는 *.class 파일을 묶어서 JVM이 운영체제로부터 할당받은 메모리 영역인 Runtime Data Area로 적재한다.
### 2. Execution Engine (실행 엔진)
  - 클래스 로더에 의해 JVM으로 로드된 *.class 파일들은 Runtime Data Area의 Method Area에 배치된다.
  - 배치된 바이트 코드를 Excution Engine에 제공하여, 바이트 코드를 실행시킨다.
  - 이렇게 로드된 바이트코드를 실행하는 런타임 모듈이 실행 엔진(Execution Engine)이다.
  - 실행 엔진은 바이트코드를 명령어 단위로 읽어서 실행한다.
### 3. Garbage Collector (가비지 컬렉터)
  - JVM은 가비지 컬렉터를 이용하여 자동으로 사용하지 않는 메모리를 회수한다.
  - 위의 장점으로 개발자는 따로 메모리 관리를 하지 않아도 된다.
  - Heap 메모리 영역에 생성된 객체들 중에 참조되지 않은 객체들을 탐색 후 제거하는 역할을 한다.
  - 역할을 정확히 언제 수행하는지는 알수 없다.
  - 가비지 컬렉터 역할을 수행하는 스레드를 제외한 나머지 스레드들은 일시정지 상태가 된다.
### 4. Runtime Data Area (런타임 데이터 영역)
  - JVM의 메모리 영역으로 자바 애플리케이션이 실행될 때 사용하는 데이터들을 적재하는 영역
  - 모든 스레드가 공유해서 사용 (GC의 대상)
    - 힙 영역 (Heap Area)
    - 메서드 영역 (Method Area)
  - 스레드마다 하나씩 생성
    - 스택 영역 (Stack Area)
    - PC 레지스터 (PC Register)
    - 네이티브 메서드 스택 (Native Method Stack)
  - 메서드 영역 (Method Aread = Static Area)
    - 클래스 멤버 변수의 이름, 데이터 타입, 접근 제어자 정보와 같은 각종 필드 정보들과 메서드 정보, 데이터 타입 정보, Constant Poll, static 변수, final class 등이 생성되는 영역
    - 프로그램이 종료 될 때까지 메모리에 남아있다.
    - GC가 동작하지 않는 영역
  - 힙 영역 (Heap Area)
    - new 키워드로 생성된 객체와 배열이 생성되는 영역
    - 주기적으로 GC가 제거하는 영역
    - String Constant Poll 은 기존에는 상수값 전용 메모리(Perm 영역)에 따로 저장되어졌는데, 런타임 중 메모리 증가로 인한 OutOfMemory Exception을 발생 시킬 수 있어 Java7 이후로는 객체와 함께 Heap 메모리영역에서 관리하고 있다.
  - 스택 영역 (Stack Area)
    - 지역변수, 파라미터, 리턴 값, 연산에 사용되는 임시 값 등이 생성되는 영역
  - PC 레지스터 (PC Register)
    - 스레드가 생성될 때마다 생성되는 영역
    - 현재 스레드가 실행되는 부분의 주소와 명령을 저장하고 있는 영역

# 7. JRE와 JDK의 차이

### JRE (Java Runtime Environment)

- 자바 실행 환경이다.
- JVM 뿐만 아니라 Java binaries, Java 클래스 라이브러리 등을 포함하고 있어 자바 프로그램의 실행을 지원한다.
- 컴파일러나 디버거(Debugger) 등의 도구는 포함하지 않는다.
- 자바 프로그램을 개발하는 것이 아니라 실행하기만 원한다면 JRE만 설치하면 된다.

### JDK (Java Development Kit)

- 자바 개발 키트이다.
- 자바 애플리케이션을 개발하기 위한 환경을 지원한다.
- JRE를 포함할 뿐만 아니라 컴파일러(javac), javadoc, jar등 개발에 유용한 도구들을 포함하고 있다.
- 개발하기 위한 개발자들은 JDK를 다운 받으면 된다.

> #### 참고
> [1] (http://www.tcpschool.com/java/java_intro_programming)<br>
> [2] (https://velog.io/@mooh2jj/JIT-%EC%BB%B4%ED%8C%8C%EC%9D%BC%EB%9F%AC%EB%9E%80)<br>
> [3] (https://quantum-jumpin.tistory.com/24)<br>
> [4] (https://coding-factory.tistory.com/828)<br>
