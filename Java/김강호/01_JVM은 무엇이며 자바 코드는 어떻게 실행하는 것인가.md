
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

