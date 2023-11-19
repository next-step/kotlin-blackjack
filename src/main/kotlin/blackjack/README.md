# README

Top Down 형식으로 객체 역할과 책임 정의
- [x] `Nickname` 객체를 만든다.
  - [x] `Nickname`은 3자 이상 10자 이하의 문자열을 가진다.
- [x] `Suit` 객체를 만든다.
    - [x] `Suit`는 `Heart`, `Diamond`, `Spade`, `Clubs`를 가진다.
- [x] `Denomination` 객체를 만든다.
    - [x] `Denomination`는 `Ace`, `Two`, `Three`, `Four`, `Five`, `Six`, `Seven`, `Eight`, `Nine`, `Ten`, `Jack`, `Queen`, `King`를 가진다.
    - [x] `Denomination`는 `Ace`를 가지고 있다면 `Ace`의 숫자를 1 또는 11로 계산할 수 있다.
- [x] `Card` 객체를 만든다.
  - [x] `Card`는 `Suit`와 `Rank`를 가진다.
- [X] `Cards` 일급 컬렉션 객체를 만든다.
    - [x] `Cards`는 `List<Card>`를 가진다.
    - [x] `Cards`가 초기화되었을 경우 52 조합의 `Card`를 생성한다.
    - [x] `Cards`는 `Card`를 섞을 수 있다. 
    - [x] `Cards`는 `Card`를 뽑을 수 있다.
    - [x] `Cards`는 `Card`가 모두 소진되었을 경우 IllegalStateException을 발생시킨다.
- [x] `Deck` 객체를 만든다.
  - [x] `Deck`은 `Card`를 가진다.
  - [x] `Deck`은 `Card`를 섞는다.
  - [x] `Deck`은 `Card`를 뽑는다.
- [ ] `PlayerDecision` 객체를 만든다.
  - [ ] `PlayerDecision`은 `Hit`, `Stand`를 가진다.
- [ ] `Player` 객체를 만든다.
  - [ ] `Player`는 `Card`를 가진다.
  - [ ] `Player`는 `Card`를 받는다.
- [ ] `Dealer` 객체를 만든다.
  - [ ] `Dealer`는 `Card`를 가진다.
  - [ ] `Dealer`는 `Card`를 지급한다.

