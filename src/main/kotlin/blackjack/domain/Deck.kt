package blackjack.domain

data class Deck(val cardList: MutableList<Card> = init()) {
    companion object {
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
