package card

data class PlayingCard(val suit: Suit, val cardRank: CardRank) {
    fun getPoint(): Int {
        return cardRank.point
    }

    fun getSuitName(): String {
        return suit.koName
    }

    override fun toString(): String {
        return "${cardRank.symbol}${suit.koName}"
    }

    companion object {
        private val CARDS: MutableMap<String, PlayingCard> = mutableMapOf()

        fun of(suit: Suit, cardRank: CardRank): PlayingCard {
            return CARDS[toKey(suit, cardRank)] ?: throw NoSuchElementException()
        }

        private fun toKey(suit: Suit, cardRank: CardRank): String {
            return suit.name + cardRank.name
        }

        init {
            for (suit in Suit.values()) {
                for (cardRank in CardRank.values()) {
                    CARDS[toKey(suit, cardRank)] = PlayingCard(suit, cardRank)
                }
            }
        }
    }
}
