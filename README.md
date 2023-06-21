# kotlin-blackjack

# 🚀 1단계 - 코틀린 DSL

## 코틀린 DSL
- 범용 언어(= 코틀린)로 작성된 프로그램의 일부
- 범용 언어와 동일한 문법을 사용한다.
- 호출 결과를 객체로 변환하기 위해 노력할 필요가 없다.
- 타입 안전성을 보장한다.
- 코틀린 코드를 원하는 대로 사용할 수 있다.

## 코틀린 DSL 실습 요구사항
```kotlin
introduce {
  name("김보라")
  company("우아한시스터즈")
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
