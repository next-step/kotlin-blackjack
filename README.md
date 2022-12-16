# kotlin-blackjack

# 🚀 2단계 - 블랙잭

## 기능 요구사항
블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

## 실행 결과
```게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

pobi, jason에게 2장의 나누었습니다.
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17
```

## 프로그래밍 요구 사항
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- 모든 엔티티를 작게 유지한다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.

--- 

# 🚀 1단계 - 코틀린 DSL

## 실습 환경 구축

블랙잭 저장소를 기반으로 미션을 진행한다. 온라인 코드 리뷰 요청 1단계 문서를 참고해 실습 환경을 구축한다.

1. 미션 시작 버튼을 눌러 미션을 시작한다.
2. 저장소에 자신의 GitHub 아이디로 된 브랜치가 생성되었는지 확인한다.
3. 저장소를 자신의 계정으로 Fork 한다.

## 좋은 개발 코드의 8가지 특징

- 잘 작동한다.
- 읽기 쉽다.
- 테스트 가능하다.
- 관리가 쉽다.
- 외관이 보기 좋다.
- 변경이 쉽다.
- 간결하다.
- 효율적이다.

## API가 깔끔하다

- 읽기 쉽다.
- 외관이 보기 좋다.
- 간결하다.

## 코틀린은 간결한 구문을 어떻게 지원하는가?

- 확장 함수
- 중위 호출
- 연산자 오버로딩
- get 메서드에 대한 관례
- 람다를 괄호 밖으로 빼내는 관례
- 수신 객체 지정 람다

## 도메인 특화 언어

DSL(Domain-specific language)
↔ 범용 프로그래밍 언어

- 선언적 언어
- 세부 실행은 언어를 해석하는 엔진에 맡긴다.
- 컴파일 시점에 제대로 검증하는 것이 어렵다.  
  e.g. SQL, 정규 표현식

## 코틀린 DSL

- 범용 언어(= 코틀린)로 작성된 프로그램의 일부
- 범용 언어와 동일한 문법을 사용한다.
- 호출 결과를 객체로 변환하기 위해 노력할 필요가 없다.
- 타입 안전성을 보장한다.
- 코틀린 코드를 원하는 대로 사용할 수 있다.

## 확장 함수 Extension functions

```kotlin
StringUtils.lastChar("Kotlin")

fun lastChar(s: String): Char {
    return s.get(s.length - 1)
}
```

```kotlin
"Kotlin".lastChar()

fun String.lastChar(): Char {
    return this.get(this.length - 1)
}
```

## 중위 표기 Infix notation

```kotlin
1.to("one")

fun Any.to(other: Any) = Pair(this, other)
```

```kotlin
1 to "one"

infix fun Any.to(other: Any) = Pair(this, other)
```

## 연산자 오버로딩 Operator overloading

```kotlin
Point(0, 1).plus(Point(1, 2))

data class Point(val x: Int, val y: Int) {
    fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}
```

```kotlin
Point(0, 1) + Point(1, 2)

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
}
```

## get 메서드에 대한 관례 Indexed access operator

```kotlin
val names = listOf("Jason", "Pobi")
names.get(0)
names[0]
```

## 람다를 괄호 밖으로 빼내는 관례 Passing a lambda to the last parameter

```kotlin
check(false, { -> "Check failed." })
```

```kotlin
check(false) { "Check failed." }
```

## 수신 객체 지정 람다 Lambda with receiver

```kotlin
val sb = StringBuilder()
sb.append("Yes")
sb.append("No")
```

```kotlin
val sb = StringBuilder()
sb.apply {
    this.append("Yes")
    append("No")
}
```

### 코틀린 DSL 실습

```kotlin
introduce {
    name("박재성")
    company("우아한형제들")
    skills {
        soft("A passion for problem solving")
        soft("Good communication skills")
        hard("Kotlin")
    }
    languages {
        "Korean" level 5
        "English" level 3
    }
}
```

힌트
```kotlin
class DslTest {
    @ValueSource(strings = ["박재성", "제이슨"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person = introduce {
            name("박재성")
            company("우아한형제들")
        }
        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.company).isEqualTo("우아한형제들")
    }
}

fun introduce(block: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(block).build()
}

class PersonBuilder {
    private lateinit var name: String
    private var company: String? = null

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun build(): Person {
        return Person(name, company)
    }
}

data class Person(val name: String, val company: String?)
```
