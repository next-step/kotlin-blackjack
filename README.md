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
  - [X] introduce 함수 구현
  
- `이력서(Resume)`
  - 이름, 회사, 스킬 목록, 언어 목록을 상태로 가진다

- `이력서 빌더(ResumeBuilder)`
  - [X] name 함수 구현
  - [X] company 함수 구현
  - [X] skills 함수 구현
  - [X] languages 함수 구현

- `스킬(Skill)`
  - 스킬 타입과 이름을 상태로 가진다

- `스킬 빌더(SkillBuilder)`
  - [X] soft 함수 구현
  - [X] hard 함수 구현 

- `언어(Language)`
  - 이름과 단계를 상태로 가진다

- `언어 빌더(LanguageBuilder)`
  - [X] 중위 표기 구현 
  



