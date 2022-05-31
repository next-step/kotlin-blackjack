# Nextstep 4기 - 블랙잭 (코틀린다운, FP, DSL)

## 1단계 - 코틀린 DSL

### 기능 요구사항

* 다음의 DSL로 구성가능한 이력서 빌더를 만들어 본다.
```kotlin
introduce {
  name("김해중")
  company("오리백숙집")
  skills {
    soft("잠자기")
    soft("숨시기")
    hard("먹기")
  }
  languages {
    "한국어" level 5
    "영어" level -3
  }
}
```

### 프로그래밍 요구사항
* 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다.

### 체크 리스트
* [x] Resume + ResumeBuilder 구현
* [x] introduce 함수 구현
* [x] name 속성 추가
* [x] company 속성 추가
* [ ] Skills + SkillsBuilder 구현
* [ ] skills 함수 구현
* [ ] Sort, Hard List 속성 추가
* [ ] Languages + LanguagesBuilder 구현
* [ ] 언어, Level(Int) 속성 추가
* [ ] level 함수를 중위 함수로 변경
