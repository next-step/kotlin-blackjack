package blackjack.exception

class InvalidInputValueException : IllegalArgumentException("입력값이 올바르지 않습니다.")

class CardDeckIsEmptyException : RuntimeException("카드 덱이 비어있습니다. 게임을 다시 진행해주세요.")
