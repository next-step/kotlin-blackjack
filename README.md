# kotlin-blackjack

## 1단계 - 코틀린 DSL

### 기능 요구사항
- 아래와 같이 자기소개 코틀린 DSL 구현
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