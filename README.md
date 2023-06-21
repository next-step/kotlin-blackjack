# kotlin-blackjack

## Step1 코틀린 DSL

- 자기소개(introduce) DSL을 작성합니다.
```
// DSL 예시)

introduce {
  name("김정욱")
  company("kakao")
  skills {
    soft("성실함")
    soft("능동성")
    soft("열정")
    hard("spring webflux")
    hard("kotlin")
    hard("nextjs")
    hard("typescript")
  }
  languages {
    "Korean" level 5
    "English" level 3
  }
}
```
