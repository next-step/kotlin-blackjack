# kotlin-blackjack

## 코틀린 DSL

### 구현 예시

```text
introduce {
  name("이요한")
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

### 구현 목록

- 최상위 함수
  - [ ] introduce 함수 구현
  
- `이력서(Resume)`
  - 이름, 회사, 스킬 목록, 언어 목록을 상태로 가진다

- `이력서 빌더(ResumeBuilder)`
  - [ ] name 함수 구현
  - [ ] company 함수 구현
  - [ ] skills 함수 구현
  - [ ] languages 함수 구현

- `스킬(Skill)`
  - 스킬 타입과 이름을 상태로 가진다

- `스킬 빌더(SkillBuilder)`
  - [ ] soft 함수 구현
  - [ ] hard 함수 구현 

- `언어(Language)`
  - 이름과 단계를 상태로 가진다

- `언어 빌더(LanguageBuilder)`
  - [ ] 중위 표기 구현 
  



