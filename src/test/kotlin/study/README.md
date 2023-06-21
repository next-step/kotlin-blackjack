# kotlin-blackjack 1단계 - 코틀린 DSL

## 수업 메모

```kotlin
val person: Person = introduce {
    this.name(value)
    // 중괄호 안의 펑션이든 파라미터든.. this가 포함되어 있다 생각하면 된다.
}
```

```kotlin
val person = introduce {
    name("홍길동")
    company("활빈당")
}
// 위랑 같은 코드
PersonBuilder().apply {
    name("홍길동")
    company("활빈당")
}
```

```kotlin
// Person.() 의미: Person에 있는 함수만 인수로 받을거에요!
fun introduce(block: PersonBuilder.() -> Unit): Person {
    // scope function. 범위 지정 함수.
    // 일종의 syntax sugar. 의미만 전달되면 된다...
    // let also run apply with
    return PersonBuilder().apply(block).build()
}
```

```kotlin
    // 문법으로 지연로딩(초기화)를 지원해준다. 굳이 "" 값을 할당하지 않아도 된다.
    // 초기화 안했는데 접근하면 UninitializedPropertyAccessException 발생
    lateinit var name: String
    var company: String? = null
```

```kotlin
class PersonBuilder {
    lateinit var name: String
    var company: String? = null

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
// 기존에 작성한 Person 클래스는 아주 못된 클래스다 아무나 name, company를 변경할 수 있음
// 우리는 불변성이 보장되는 Person 클래스를 만들고 싶다..!
// 그래서 가변 클래스 (PersonBuilder) 로 만든다.

// 실무에서 dsl은 보통 이런 빌더를 만들기 위해 사용한다.
// 인자가 두개면..! Person("..", "..")으로 간단히 만들 수 있지만
// 인자가 많고 복잡할 때 조금 더 사람친화적인 api를 제공하고 싶다면 dsl을 사용할 수 있다.

// 인수테스트. 블랙박스 테스트(외부 api 테스트) 할 때
// 데이터 셋업 (given절) 하는게 제일 귀찮다~~~
// 테스트 셋업에 대한 dsl을 만들어 둔다.
// 스프링 배치를 dsl로 만든 사례도 있다 (라인)
```
