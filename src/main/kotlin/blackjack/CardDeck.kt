package blackjack

@JvmInline
value class CardDeck(
    private val values: ArrayDeque<Card> = ArrayDeque(listOf())
) {

    fun size() = this.values.size

    fun draw(): Card {
        return this.values.lastOrNull() ?: throw IllegalArgumentException("더이상 뽑을 카드가 없습니다.")
    }

    companion object {
        fun createDeck(): CardDeck {
            return CardDeck(ArrayDeque(STANDARD_CARD_DECK))
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
