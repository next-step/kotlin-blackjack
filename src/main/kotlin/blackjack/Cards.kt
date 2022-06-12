package blackjack

@JvmInline
value class Cards(
    private val values: ArrayDeque<Card> = ArrayDeque(listOf())
) {

    fun size() = this.values.size

    fun add(card: Card) {
        this.values.add(card)
    }

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
