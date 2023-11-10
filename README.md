# kotlin-blackjack

## 기능 요구사항
블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

## 실행 결과
```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

pobi, jason에게 2장의 나누었습니다.
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17
```

## 프로그래밍 요구사항
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- 모든 엔티티를 작게 유지한다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
- git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.

## 요구사항 분석
- [x] 카드는 모양과 숫자로 구성된다.
- [x] 카드는 1부터 10까지 숫자, J, Q, K로 구성된다.
- [x] 카드는 4가지 모양(하트, 다이아몬드, 스페이드, 클로버)로 구성된다.
- [x] Ace는 1 또는 11로 계산할 수 있다.
- [x] King, Queen, Jack은 각각 10으로 계산한다.
- [x] 게임을 시작하면 플레이어는 두 장의 카드를 지급 받는다.
- [x] 카드 지급시 카드는 무작위로 지급된다.
- [x] 카드는 중복해서 지급할 수 없다.
- [x] 플레이어는 카드를 계속 뽑을 수 있다.
- [x] 플레이어는 카드를 더 이상 뽑지 않을 수 있다.
- [x] 플레이어가 뽑은 카드의 합을 계산한다.
- [x] 게임에 참여할 사람의 이름을 입력받는다.
- [ ] 딜러는 처음 두 장을 받는다.
- [ ] 딜러는 16이하면 무조건 한 장을 더 받는다.
- [ ] 딜러는 17이상이면 더 이상 카드를 받지 않는다.
- [ ] 딜러는 처음 카드 공개시 한 장만 공개한다.
- [ ] 딜러와 플레이어 점수 비교하여 승자를 가린다.