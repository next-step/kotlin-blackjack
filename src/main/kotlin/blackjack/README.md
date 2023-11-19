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
- [ ] `Cards` 일급 컬렉션 객체를 만든다.
    - [x] `Cards`는 `Card`를 가진다.
    - [ ] `Cards`는 `Card`가 0개 이상 52개 이하를 가지는지 유효성 검증을 담당한다.
- [ ] `Deck` 객체를 만든다.
  - [ ] `Deck`은 `Card`를 가진다.
  - [ ] `Deck`은 `Card`를 섞는다.
  - [ ] `Deck`은 `Card`를 뽑는다.
- [ ] `PlayerDecision` 객체를 만든다.
  - [ ] `PlayerDecision`은 `Hit`, `Stand`를 가진다.
- [ ] `Player` 객체를 만든다.
  - [ ] `Player`는 `Card`를 가진다.
  - [ ] `Player`는 `Card`를 받는다.
- [ ] `Dealer` 객체를 만든다.
  - [ ] `Dealer`는 `Card`를 가진다.
  - [ ] `Dealer`는 `Card`를 지급한다.

