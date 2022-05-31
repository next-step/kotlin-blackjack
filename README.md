# kotlin-blackjack
***
## 코틀린 DSL
아래와 같은 형식의 코틀린 DSL을 개발한다
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
- [x] introduce는 name, company, skills, languages를 받는다
- [x] name과 company는 문자열을 입력으로 받는다
- [x] skills는 soft와 hard를 받는다
  - [x] soft 함수
  - [x] hard 함수
- [x] languages는 언어와 그에 따른 레벨을 입력으로 받는다
  - [x] infix 함수 level이 필요하다