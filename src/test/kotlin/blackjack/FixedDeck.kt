package blackjack

class FixedDeck : Deck {
    override fun draw(): Card? {
        return CACHED_CARDS.removeLastOrNull()
    }

    companion object {
        private val CACHED_CARDS =
            mutableListOf(
                Card(CardNumber.ACE, CardMark.HEART),
                Card(CardNumber.TEN, CardMark.HEART),
                Card(CardNumber.ACE, CardMark.HEART),
                Card(CardNumber.TEN, CardMark.HEART),
            )
    }
}
