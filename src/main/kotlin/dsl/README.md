# 🚀 1단계 - 코틀린 DSL

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

### 기능 예상
- [x] `introduce { }` ->  람다를 괄호 밖으로 빼내는 관례 이용 
- [x] `name` -> 수신 객체 지정 람다
- `company` -> 수신 객체 지정 람다
- `skills` -> 수신 객체 지정 람다 -> Skiil
  - `soft`, `hard` -> N개 등록 ?  
- `languages` -> 수신 객체 지정 람다
  - `Korean" level 5` -> 중위 표기