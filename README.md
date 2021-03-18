# kotlin-blackjack

## 코틀린 DSL 실습
```
introduce {
    name("서진혁")
    company("nextstep")
    skills {
        soft ("A passion for problem solving")
        soft ("Good communication skills")
        hard ("Kotlin")
    }
    languages {
        "Korean" level 5
        "English" level 3
    }
}
```

## 블랙잭
- 하트,다이아,스페이드,클로버 타입을 가지는 카드 타입을 생성한다
- 카드들을 가지는 일급컬렉션을 생성하여 점수를 계산하는 로직을 구현한다
- 이름과 카드덱을 가지는 플레이어 객체를 생성한다
- 카드를 랜덤하게 반환하는 로직을 구현한다
- 이름을 입력하는 view로직을 작성한다. 
- 처음 2장의 카드를 받는다.
- 플레이어가 원한다면 한장의 카드를 더 받을수 있도록 하며 21이 넘을 경우 더 이상 뽑을수 없습니다.
- 최종 결과를 출력합니다.
