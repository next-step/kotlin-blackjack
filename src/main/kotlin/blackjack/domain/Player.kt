package blackjack.domain

import kotlin.math.abs
import kotlin.math.min

open class Player(open val name: PlayerName) {

    var gameResultState: GameResultState = GameResultState.DRAW

    val cards: Cards = Cards(hashSetOf())

    fun addCard(card: Card?) {
        if (card != null) {
            cards.addCard(card)
        }
    }

    fun getCardScore(): Int {
        val minScore = cards.getTotalScore()
        val hasAce = cards.hasAceCard()
        val maxScore = minScore + if (hasAce) MAX_PLUS_SCORE else MIN_PLUS_SCORE
        if (maxScore > WIN_SCORE) {
            return minScore
        }
        return if (min(abs(WIN_SCORE - minScore), abs(WIN_SCORE - maxScore)) == abs(WIN_SCORE - minScore)) {
            minScore
        } else {
            maxScore
        }
    }

    fun setResultState(state: GameResultState) {
        gameResultState = state
    }

    companion object {
        const val WIN_SCORE = 21
        const val MAX_PLUS_SCORE = 10
        const val MIN_PLUS_SCORE = 0
    }
}
