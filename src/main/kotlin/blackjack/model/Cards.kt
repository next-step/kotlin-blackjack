package blackjack.model

class Cards private constructor(private val cards: List<Card>) : List<Card> by cards {
    fun countAce(): Int {
        return cards.count { it.isAce() }
    }

    fun getHighestScore(): Score {
        return cards.fold(Score.ZERO) { acc, card -> acc + card.getScores().highest() }
    }

    companion object {
        const val INITIAL_DRAW_COUNT = 2

        private fun firstDraw() = (1..INITIAL_DRAW_COUNT).map { Card.get() }
    }

    class Builder {
        private var cards: List<Card> = firstDraw()

        fun cards(cards: List<Card>): Builder {
            this.cards = cards
            return this
        }

        fun build(): Cards {
            return Cards(cards.toList())
        }
    }
}
