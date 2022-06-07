# 1단계: 코틀린 DSL

## 요구사항
- 아래와 같이 코드를 작성할 경우 Person 클래스가 생성된다.
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

## To Do
- [x] introduce 함수가 작동해야 한다
- [x] name 함수가 작동해야 한다
- [x] company 함수가 작동해야 한다
- [x] skills 함수가 작동해야 한다
- [x] languages 함수가 작동해야 한다
- [x] 리팩토링

## 리뷰 반영
1. enum 대신 sealed class 사용하기
2. 패키지 명 dsl 로 바꾸기
3. 리스트를 비교할 때에는 isEqualTo 보다는 containsExactly... 메소드를 활용하기
4. SkillLevel 을 따로 구현하지 않도록 Skill 을 sealed interface 로 구현하기