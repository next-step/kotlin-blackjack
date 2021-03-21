package blackjack.model

class Cards private constructor(private val cards: List<Card> = firstDraw()) : List<Card> by cards {
    fun countAce(): Int {
        return cards.count { it.isAce() }
    }

    fun getHighestScore(): Score {
        return cards.fold(Score.ZERO) { acc, card -> acc + card.getScores().highest() }
    }

    override fun toString(): String {
        return cards.joinToString(separator = ", ") { "$it" }
    }

    companion object {
        const val INITIAL_DRAW_COUNT = 2

        private fun firstDraw() = (1..INITIAL_DRAW_COUNT).map { Card.get() }
    }

    class Builder {
        private var cards: List<Card>? = null

        fun cards(cards: List<Card>): Builder {
            this.cards = cards
            return this
        }

        fun build(): Cards {
            if (cards != null) {
                return Cards(cards!!.toList())
            }
            return Cards()
        }
    }
}
