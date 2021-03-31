package blackjack.model.trump

import blackjack.model.score.Score

class Cards private constructor(private val cards: List<Card>) : List<Card> by cards {
    fun countAce(): Int {
        return cards.count { it.isAce() }
    }

    fun getHighestScore(): Score {
        return cards.fold(Score.ZERO) { acc, card -> acc + card.getScores().highest() }
    }

    fun draw(deck: Deck): Cards {
        val mutableCards = cards.toMutableList()
        mutableCards.add(deck.draw())

        return Cards(mutableCards.toList())
    }

    companion object {
        const val INITIAL_DRAW_COUNT = 2

        fun firstDraw(deck: Deck) = Cards((1..INITIAL_DRAW_COUNT).map { deck.draw() })
    }

    class Builder {
        private var cards: List<Card> = listOf()

        fun cards(cards: List<Card>): Builder {
            this.cards = cards
            return this
        }

        fun build(): Cards {
            return Cards(cards.toList())
        }
    }
}
