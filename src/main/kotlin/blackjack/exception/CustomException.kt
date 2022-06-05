package blackjack.exception

class InvalidInputValueException : IllegalArgumentException("입력값이 올바르지 않습니다.")

class CardDeckIsEmptyException : RuntimeException()
