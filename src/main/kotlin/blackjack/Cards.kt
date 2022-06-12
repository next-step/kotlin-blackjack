package blackjack

@JvmInline
value class Cards(private val values: List<Card>) {

    fun size() = this.values.size

    companion object {
        fun createDeck(): Cards {
            return Cards(ArrayDeque(STANDARD_CARD_DECK))
        }

        private val STANDARD_CARD_DECK: ArrayDeque<Card> = initCardDeck()

        private fun initCardDeck(): ArrayDeque<Card> {
            val list: MutableList<Card> = mutableListOf()
            for (suit in CardSuit.values()) {
                for (number in CardNumber.values()) {
                    list.add(Card(number, suit))
                }
            }
            return ArrayDeque(list)
        }
    }
}
