package blackjack.domain

class Dealer(private val deck: Deck) {
    fun draw(): Card {
        return deck.cardList
            .removeFirstOrNull() ?: throw IllegalStateException(CANNOT_DRAW_CARD_MESSAGE)
    }

    companion object {
        private const val CANNOT_DRAW_CARD_MESSAGE = "뽑을 수 있는 카드가 없습니다."
    }
}
