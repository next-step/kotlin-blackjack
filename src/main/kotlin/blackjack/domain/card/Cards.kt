package blackjack.domain.card

data class Cards internal constructor(val cards: List<Card>) : List<Card> by cards {
    init {
        require(cards.toSet().size == cards.size) {
            throw IllegalArgumentException(CARDS_DUPLICATED)
        }
    }

    fun addCards(card: Card): Cards {
        return Cards(cards + card)
    }

    fun hasAce(): Boolean {
        return cards.any { it.denomination.isAce() }
    }

    fun getHighestPoint(): Int {
        if (!hasAce()) {
            return getTotalPoint()
        }
        if (hasAce() && getTotalPointWithAce() <= TARGET_GAME_POINT) {
            return getTotalPointWithAce()
        }
        return getTotalPoint()
    }

    fun isBlackJack(): Boolean {
        return cards.size == 2 && getHighestPoint() == 21
    }

    private fun getTotalPoint(): Int {
        return cards.fold(0) { acc, card ->
            acc + card.denomination.point.first
        }
    }

    private fun getTotalPointWithAce() = getTotalPoint() + ACE_APPLY_POINT

    companion object {
        private const val CARDS_DUPLICATED = "카드가 중복되었습니다."
        private const val ACE_APPLY_POINT = 10
        private const val TARGET_GAME_POINT = 21

        val EMPTY = Cards(emptyList())

        fun from(cards: List<Card>): Cards {
            if (cards.isEmpty()) return EMPTY
            return Cards(cards)
        }
    }
}
