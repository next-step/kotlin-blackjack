package blackjack.domain

data class Deck(val cardList: MutableList<Card> = createDeck()) {
    companion object {
        private fun createDeck(): MutableList<Card> {
            return Suit.entries.flatMap { suit ->
                CardNumber.entries.map { cardNumber ->
                    Card(suit, cardNumber)
                }
            }.toMutableList()
        }
    }
}
