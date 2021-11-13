package blackjack

data class Cards internal constructor(val cards: List<Card>) : List<Card> by cards {
    init {
        require(cards.toSet().size == cards.size) {
            throw IllegalArgumentException(CARDS_DUPLICATED)
        }
    }

    fun addCards(card: Card): Cards {
        return Cards(cards + card)
    }

    companion object {
        private const val CARDS_DUPLICATED = "카드가 중복되었습니다."

        val EMPTY = Cards(emptyList())

        fun from(cards: List<Card>): Cards {
            if (cards.isEmpty()) return EMPTY
            return Cards(cards)
        }
    }
}
