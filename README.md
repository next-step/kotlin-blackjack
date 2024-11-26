# kotlin-blackjack

# 1단계, 코틀린 DSL
```kotlin
introduce {
  name("홍길동")
  company("활빈당")
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

### 기능목록
[ v ] Person

- name
- company

[ v ] Skills

[ v ] Skill

- sealed interface, soft, hard

[ v ] Languages

- infix level method

[ v ] Language