# kotlin-blackjack

## Step 1. 코틀린 DSL

- 아래 DSL을 동작하게 하는 코드를 작성한다.

```
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