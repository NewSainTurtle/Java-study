## JVM이란?

JVM(Java Virtual Machine)은 자바 프로그램 실행환경을 만들어주는 소프트웨어이다.

작성한 자바 코드(`.java`)를 자바 컴파일러에서 컴파일하면 바이트 코드(`.class`)가 된다.

JVM은 바이트 코드를 운영체제에 맞는 실행파일로 바꿔준다. 

자바로 작성된 애플리케이션은 모두 JVM에서만 실행되기 때문에, 자바 애플리케이션이 실행되기 위해서는 반드시 JVM이 필요하다.

> Java는 어떤 플랫폼에도 영향을 받지 않는다.
> 

![C Java](https://github.com/NewSainTurtle/CS-study/assets/26339069/39cf4834-6240-495c-8021-4d849672fff4)

C 코드를 실행하는 경우에, 윈도우 컴파일러를 통해 컴파일을 하여 `Test.exe` 가 만들어졌다면 이는 윈도우에서만 실행가능하다. C나 C++은 컴파일 플랫폼과 실행 플랫폼이 다르면 프로그램이 동작하지 않는다.

반면에 Java 코드는 컴파일하면 바이트 코드가 생성되도, 각자 플랫폼에 설치되어있는 JVM이 운영체제에 맞는 실행파일로 바꿔준다.  

⭐️ **Java는 플랫폼에 종속적이지 않지만, JVM은 플랫폼에 종속적이다.**

---

### 자바 프로그램 실행과정

![자바 프로그램 실행과정](https://github.com/NewSainTurtle/CS-study/assets/26339069/66d0660a-ea08-45ae-8b9e-12fe85253c37)

1. 자바 코드 `.java` 작성 
2. 터미널에 있는 자바 컴파일러인 **javac**에 컴파일 명령을 통해 바이트 코드 `.class` 파일 생성
3. 바이트 코드는 **클래스 로더**를 통해 **JVM Runtime Data Area**로 로딩
4. 로딩된 바이트 코드를 실행할 컴퓨터에 설치된 JVM에게 전달
5. 해당 컴퓨터가 이 프로그램을 실행할 때 마다 JVM이 기계어로 해석

**Tip💡** 

바이트 코드(Bytecode)는 특정 하드웨어가 아닌 가상 컴퓨터(VM)에서 돌아가는 실행 프로그램을 위한 이진 표현법이다.<br>
바이트 코드는 JVM이 이해하는 이진 코드라면, 기계어는 OS가 이해하는 이진 코드이다.

---

## 바이트 코드를 읽는 방식

JVM은 바이트 코드를 명령어 단위로 읽어서 해석하는 데, 인터프리터 방식과 JIT 컴파일 방식을 사용한다.

### Interpreter

바이트 코드를 한 줄씩 해석하고 실행한다.

인터프리터 방식은 초기 방식으로, 실행 속도가 느리다는 단점이 있다.

### JIT(Just In Time)

인터프리터 방식의 단점을 보완하기 위해 생겼다.

프로그램을 실행하는 시점(바이트 코드를 실행하는 시점)에 바이트 코드를 각 운영체제에 맞는 네이티브 코드로 변환한다.

하드웨어가 발전하면서 자바 컴파일러도 JIT 컴파일 방식을 통해 실행 속도가 개선되었다.

하지만, JIT 컴파일 방식을 사용하는 경우 변환하는 과정에서 비용이 든다.

인터프리터 방식을 사용하다가 일정기준을 넘기면 JIT 컴파일 방식으로 명령어를 실행한다.

![JIT](https://github.com/NewSainTurtle/CS-study/assets/26339069/caafde2a-1bab-4f30-a7a7-e57d65ae596f)

JVM은 JIT 컴파일러를 지원한다. JIT 컴파일러는 같은 코드를 매번 해석하지 않고, 처음 실행할 때 컴파일하면서 해당 코드를 **캐싱**한다.<br>
이후에는 바뀐 부분만 컴파일한다.

---

## JVM 동작 방식

![JVM 동작 방식](https://github.com/NewSainTurtle/CS-study/assets/26339069/169b67bf-cb66-42e2-a130-6f6a95dd6b7e)

1. 자바로 개발된 프로그램 `.java` 을 실행하면 JVM은 운영체제로부터 **메모리를 할당**
2. 터미널에 있는 자바 컴파일러인 **javac** 가 컴파일 명령을 통해 바이트 코드 `.class` 파일 생성
3. 바이트 코드는 **클래스 로더**를 통해 **JVM 런타임 데이터 영역**으로 로딩
4. 로딩된 바이트 코드를 **실행 엔진**을 통해 해석
5. 해석된 바이트 코드는 **런타임 데이터 영역**의 각 영역에 배치되어 수행
이 과정에서 실행 엔진에 의해 GC의 작동과 스레드 동기화가 이루어짐.

---

## JVM 구성 요소

### Class Loader

자바는 동적으로 클래스를 읽어오기 때문에, 런타임 때 모든 코드가 JVM과 연결된다.

**클래스로더**는 동적으로 클래스를 로딩한다. 

클래스로더는 `.class` 파일을 묶어서 JVM이 운영체제로부터 할당받은 메모리 영역인 **런타임 데이터 영역**으로 적재한다.

이 때, 모든  `.class` 파일을 한 번에 메모리에 올리는 것이 아니라 필요할 때마다 동적으로 올려준다.

클래스 로더는 **로딩(클래스 읽기) -> 링킹(레퍼런스 연결) -> 초기화의 단계**로 동작한다.

### Execution Engine

**클래스로더**에 의해 JVM으로 로드된 바이트 코드들은 **런타임 데이터 영역**에 배치된다.

배치된 이후에 JVM은 메소드 영역**의 바이트 코드**를 **실행 엔진**에 제공한다.

실행 엔진은 바이트 코드를 명령어 단위로 읽어서 정의된 내용대로 실행한다.

로드된 바이트 코드를 실행하는 런타임 모듈이 **실행 엔진**이다.

### Garbage Collector

JVM 메모리 중 힙 영역에서 사용하지 않는 객체를 자동으로 관리해주는 역할을 한다.

➕ 장점

- 자동으로 메모리를 관리해주기 때문에 메모리 누수 발생이 적어진다.
- 이미 해제된 메모리를 다시 해제하는 오류를 줄일 수 있다.

➖ 단점

- GC가 동작하는 타이밍이나 점유 시간을 사전에 예측하기 어렵다. → 실시간 시스템에는 적합하지 않다.
- GC가 동작하는 동안에는 다른 동작을 멈추기 때문에 오버헤드가 발생한다.

    
**STW(Stop The World)**
    
Stop The World는 GC를 실행하기 위해 JVM이 애플리케이션의 실행을 멈추는 작업이다.
    
GC가 동작하면 GC를 실행하는 스레드를 제외한 나머지 스레드는 작업을 멈춘다.
    
**Mark and Sweep 알고리즘**
    
![Mark and Sweep](https://github.com/NewSainTurtle/CS-study/assets/26339069/63751562-e7e7-4ee2-b1ec-da4c2f144c07)

1. **Mark :** 먼저 루트로부터 그래프 순회를 통해 연결된 객체들을 찾아내 각각 어떤 객체를 잠조하고 있는지 찾아서 마킹함
2. **Sweep :** Unreachable 객체들을 Heap에서 제거
3. **Compact :** Sweep 후에 분산된 객체들을 Heap의 시작 주소로 모아 메모리가 할당된 부분과 그렇지 않은 부분으로 압축 (가비지 컬렉터 종류에 따라 하지 않는 경우도 있음)
    
**GC의 구조**
    
![GC](https://github.com/NewSainTurtle/CS-study/assets/26339069/f1a2514f-e240-4fee-8045-7435b6615430)

- Young 영역(Young Generation)
    - 새롭게 생성된 객체가 할당되는 영역
    - 대부분의 객체가 금방 Unreachable 상태가 되기 때문에, 많은 객체가 Young 영역에 생성됐다가 사라진다.
    - Young 영역에 대한 GC를 `Minor GC`라고 부른다.
- Old 영역(Old Generation)
    - Young 영역에서 Reachable 상태를 유지하여 살아남은 객체가 복사되는 영역
    - 복사되는 과정에서 대부분 Young 영역보다 크게 할당되며, 크기가 큰 만큼 가비지는 적게 발생한다.
    - Old 영역에 대한 GC를 `Major GC`(또는 Full GC)라고 부른다.
- Permanent
    - java 8부터 없어짐
    - Perm 영역은 보통 클래스나 메소드의 Meta 정보, static 변수와 상수 정보들이 저장되는 공간으로 흔히 메타데이터 저장 영역이라고도 한다. 이 영역은 Java 8 부터는 Native 영역으로 이동하여 Metaspace 영역으로 변경되었다.
    
****Minor GC의 동작 방식****
    
Young 영역은 1개의 Eden 영역과 2개의 Survivor 영역, 총 3가지 영역으로 나뉘어져있다.
    
- Eden 영역: 새로 생성된 객체가 할당(Allocation)되는 영역
- Survivor 영역: 최소 1번의 GC 이상 살아남은 객체가 존재하는 영역
    
1. 새로 생성된 객체가 Eden 영역에 할당(Allocation)된다.

2. 객체가 계속 생성되어 Eden 영역이 가득 차게 되고 Minor GC가 실행된다.
   1. Eden 영역에서 사용되지 않는 객체의 메모리가 해제된다.
    2. Eden 영역에서 살아남은 객체는 age 값을 증가시키고, 1개의 Survivor 영역으로 이동하며 Eden 영역은 비워진다.

3. 객체가 계속 생성되어 또 다시 Eden 영역이 가득 차게 되면 Minor GC가 실행된다.
   1. Eden과 Survivor 영역에서 사용되지 않는 객체의 메모리가 해제된다.
    2. Survivor 영역의 살아남은 객체를 age 값을 증가시키고, 다른 Survivor 영역으로 이동시킨다. 
       (1개의 Survivor 영역은 반드시 빈 상태여야 한다.)
    3. Eden 영역에서 살아남은 객체는 age 값을 증가시키고, 1개의 Survivor 영역으로 이동하며 Eden 영역은 비워진다.

4. 이러한 과정을 반복하여 계속해서 살아남은 객체(특정 age 값을 넘어가는 경우)는 Old 영역으로 이동(Promotion)된다.
    
****Major GC의 동작 방식****
    
Young 영역에서 오래 살아남은 객체는 Old 영역으로 이동(Promotion)된다. 
    
그리고 **Major GC**는 객체들이 계속 이동해 Old 영역의 메모리가 부족해지면 발생하게 된다. 
    
Young 영역은 일반적으로 Old 영역보다 크키가 작기 때문에 GC가 보통 0.5초에서 1초 사이에 끝난다. 그렇기 때문에 Minor GC는 애플리케이션에 크게 영향을 주지 않는다. 
    
하지만 Old 영역은 Young 영역보다 크며 Young 영역을 참조할 수도 있다. 그렇기 때문에 Major GC는 일반적으로 Minor GC보다 시간이 오래걸리며, 10배 이상의 시간을 사용한다.
    
**Tip💡** 
    
Reachable : 객체가 참조되고 있는 상태
    
Unreachable  : 객체가 참조되고 있지 않은 상태 (GC의 대상이 됨)
    

### Runtime Data Area

![Runtime Data Area](https://github.com/NewSainTurtle/CS-study/assets/26339069/9be909f4-29b0-47b9-a1e2-c6fbf18369ed)

런타임 데이터 영역은 JVM의 메모리 영역으로, 자바 애플리케이션을 실행할 때 사용되는 데이터들을 적재하는 영역이다.

- 모든 스레드가 공유해서 사용
    - Method Area
    - Heap Area (GC 대상)
- 스레드마다 하나씩 생성
    - Stack Area
    - PC Register
    - Native Method Stack

### Method Area

클래스의 정보가 저장되는 영역이다.

클래스의 멤버 변수의 이름과 타입, 메소드의 이름과 파라미터, 반환값이 저장되고, 각종 상수, static 메소드, final 클래스 변수 등이 저장된다.

메소드 영역에는 **Runtime Constant Pool**이라는 별도의 관리 영역이 있는데, 여기에 상수 자료형을 저장하여 중복을 막는 역할을 한다.

### Heap Area

new 키워드에 의해 생성되는 클래스와 배열 등이 저장되는 영역이다.

메소드 영역에 저장된 클래스만이 생성 가능하고, 가비지 컬렉터에 의해 사용되지 않은 클래스나 배열이 제거된다.

### Stack Area

지역 변수, 파라미터, 반환 값, 연산에 사용되는 임시값들이 생성되는 영역이다.

`Person p = new Person()` 와 같이 클래스를 생성하는 경우 `new`에 의해 생성된 클래스는 **힙 영역**에 저장되고, 클래스의 참조인 p는 **스택 영역**에 저장된다.

### PC Register

스레드가 생성될 때마다 생성되며, 현재 실행중인 주소와 명령을 저장하는 영역이다.

멀티 스레드가 동작할 때, 이곳의 정보를 이용해 여러 스레드를 동시에 실행할 수 있다.

### Native Method Stack

자바가 아닌 다른 언어로 작성된 코드를 실행할 때 사용하는 영역이다.

즉, JVM 위에서 자바 코드가 아닌 C, C++, 어셈블리 같은 다른 언어들로 작성된 라이브러리를 호출하거나 반대로 호출되는 것을 가능하게 한다.

---

## JRE와 JDK의 차이

![JRE와 JD](https://github.com/NewSainTurtle/CS-study/assets/26339069/564c3de1-ee12-4b95-8631-9d2879495043)

### JRE (Java Runtime Environment)

JRE는 자바 실행 환경으로, 자바로 만들어진 프로그램을 실행시키는 데 필요한 라이브러리, API, JVM이 포함되어 있다.

자바를 실행시킬 수 있지만, 개발할 수는 없다. (읽기O, 쓰기X)

### JDK (Java Development Kit)

JDK는 자바 개발 키트로 개발자들이 자바로 개발하는데 사용된다.

JDK안에는 개발하는 데 필요한 라이브러리, javac 등의 개발 도구들이 포함되어 있다.

또한, 개발하기 위해서는 실행시켜야 하기 때문에 JRE도 포함되어 있다.

⭐️ **자바로 만들어진 프로그램을 실행시키려면 JRE가 필요하고, 프로그램을 직접 개발하려면 JDK가 필요하다.**

> 참고<br>
> [1] [https://coding-factory.tistory.com/827](https://coding-factory.tistory.com/827)<br>
> [2] [https://aboullaite.me/understanding-jit-compiler-just-in-time-compiler/](https://aboullaite.me/understanding-jit-compiler-just-in-time-compiler/)<br>
> [3] [https://catch-me-java.tistory.com/11](https://catch-me-java.tistory.com/11)<br>
> [4] [https://itkjspo56.tistory.com/285](https://itkjspo56.tistory.com/285)<br>
