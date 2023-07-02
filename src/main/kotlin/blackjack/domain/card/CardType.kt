package blackjack.domain.card

enum class CardType {
    SPADE,
    CLOVER,
    HEART,
    DIAMOND;

    companion object {
        fun getCardDeck(numbers: Array<CardNumber>): BlackCardDeck {
            return BlackCardDeck(
                values().flatMap {
                    getCard(numbers, it)
                }.shuffled().toMutableSet()
            )
        }

        private fun getCard(numbers: Array<CardNumber>, it: CardType) = numbers.map { cardNumber ->
            Card(cardNumber, it)
        }
    }
}
