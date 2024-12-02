package blackjack.domain

data class Deck(val cardList: MutableList<Card> = createDeck()) {
    companion object {
        private fun createDeck(): MutableList<Card> {
            return MutableList(52) { Card(Suit.CLUBS, CardNumber.ACE) }
        }
    }
}
