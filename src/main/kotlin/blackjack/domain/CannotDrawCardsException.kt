package blackjack.domain

class CannotDrawCardsException : RuntimeException("더이상 뽑을 수 있는 카드가 없습니다.")