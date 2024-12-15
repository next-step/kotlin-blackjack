package blackjack.domain

data class Deck(val cardList: MutableList<Card> = init()) {
    fun draw(): Card {
        return cardList
            .removeFirstOrNull() ?: throw IllegalStateException(CANNOT_DRAW_CARD_MESSAGE)
    }

    companion object {
        private const val CANNOT_DRAW_CARD_MESSAGE = "뽑을 수 있는 카드가 없습니다."

        private fun init(): MutableList<Card> {
            return createDeck().shuffled().toMutableList()
        }

        private fun createDeck(): MutableList<Card> {
            return Suit.entries.flatMap { suit ->
                CardNumber.entries.map { cardNumber ->
                    Card(suit, cardNumber)
                }
            }.toMutableList()
        }
    }
}
