# 3주차 : kotlin-blackjack

## Step1 : 코틀린DSL

### 실습내용

- 확장함수 사용
- infix 사용
- get 메서드에 대한 관례
- 람다를 괄호밖으로 빼내는 관례
- 수신 객체 지정 람다 (apply)

```kotlin
introduce {
    name("윤도현")
    company("카카오")
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
