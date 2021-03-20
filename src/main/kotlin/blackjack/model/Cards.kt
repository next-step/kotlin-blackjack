package blackjack.model

class Cards private constructor(private val cards: List<Card> = firstDraw()) : List<Card> by cards {
    fun getScore(): Score {
        var aceCount = cards.count { it.isAce() }
        var score = cards.fold(Score.ZERO) { acc, card -> acc + card.getScores().highest() }

        while (score > Score.MAXIMUM && aceCount > 0) {
            score -= (CardNumber.ACE.scores.highest() - CardNumber.ACE.scores.lowest())
            aceCount--
        }

        return score
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
