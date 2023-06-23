package blackjack.domain

enum class CardType {
    SPADE,
    CLOVER,
    HEART,
    DIAMOND;

    companion object {
        fun getCardDeck(numbers: Array<CardNumber>): Cards {
            return Cards(
                values().flatMap {
                    getCard(numbers, it)
                }.shuffled().toMutableList()
            )
        }

        private fun getCard(numbers: Array<CardNumber>, it: CardType) = numbers.map { cardNumber ->
            Card(cardNumber, it)
        }
    }
}
