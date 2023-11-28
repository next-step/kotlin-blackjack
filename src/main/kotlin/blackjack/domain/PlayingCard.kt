package blackjack.domain

@JvmInline
value class PlayingCard (val cards: Cards) {
    init {
        require(cards.cards.toSet().size == 52) { "카드의 개수는 반드시 52장이어야 합니다." }
    }
}
