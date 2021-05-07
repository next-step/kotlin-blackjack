package blackjack.model.trump

import blackjack.model.BlackJackRule
import blackjack.model.score.Score

class Cards private constructor(private val cards: Set<Card>) : Set<Card> by cards {
    val initializing: Boolean
        get() = cards.size < INITIAL_DRAW_COUNT

    val isBlackJack: Boolean
        get() = getHighestScore().isMaximum() && isNoAdditionalDraw()

    val isValid: Boolean
        get() = score.isValid()

    val score: Score
        get() = BlackJackRule.getScore(this)

    constructor(cards: List<Card>) : this(cards.toSet())

    constructor(vararg cards: Card) : this(cards.toSet())

    fun countAce(): Int {
        return cards.count { it.isAce() }
    }

    fun getHighestScore(): Score {
        return cards.fold(Score.ZERO) { acc, card -> acc + card.getScores().highest() }
    }

    fun draw(deck: Deck): Cards {
        val mutableCards = cards.toMutableSet()
        deck.draw()?.let { mutableCards.add(it) }

        return Cards(mutableCards.toSet())
    }

    private fun isNoAdditionalDraw(): Boolean {
        return cards.size == INITIAL_DRAW_COUNT
    }

    operator fun plus(other: Card): Cards {
        return Cards(cards + other)
    }

    companion object {
        const val INITIAL_DRAW_COUNT = 2

        fun firstDraw(deck: Deck) = Cards((1..INITIAL_DRAW_COUNT).mapNotNull { deck.draw() })
    }
}
