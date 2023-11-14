# kotlin-blackjack

# Step1- KotlinDSL 
필요 도메인 
- 사람 ( Person )
  - 이름 ( Name )
  - 회사 ( Company )
  - 스킬 ( Skill )

- 회사 ( Company )
  - 이름 ( Name )

- 스킬 ( Skill )
  - 설명 ( introduce )

- 언어 ( Language )
    - 이름 ( Name )
    - 레벨 ( Level )

## 고려했던 상황 
- 언어 레벨의 경우 1~5까지만 선택할 수 있다. 
- 각 도메인들은 빈값 ("")이 들어올 경우, 예외가 발생한다.
