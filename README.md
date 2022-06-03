# kotlin-blackjack


## step 1 코틀린 DSL 실습
```
introduce {
  name("김승갑")
  company("kakao")
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

### 구현 목록
- [x] Person 객체를 생성한다.
- [x] Person은 name 정보를 갖으며 DSL로 생성한다.
- [ ] Person은 company 정보를 갖으며 DSL로 생성한다.
- [ ] Person은 skills 정보를 갖으며 DSL로 생성한다.
    - [ ] Skill에는 hard, soft 정보로 구성하며 DSL로 생성한다.
- [ ] Person은 languages 정보를 갖으며 DSL로 생성한다.
    - [ ] languages는 operator로 Subject, Level 정보를 추가한다.
