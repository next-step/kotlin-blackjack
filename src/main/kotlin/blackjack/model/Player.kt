package blackjack.model

import kotlin.math.abs

const val WIN_SCORE = 21

open class Player(val name: String, private val cards: Cards = Cards(emptyList())) {
    var gameResult: GameResult = GameResult.LOSE
        private set

    fun draw(vararg cards: Card) {
        repeat(cards.size) {
            this.cards.add(cards[it])
        }
    }

    fun score(): Int {
        return cards.score()
    }

    fun available(): Boolean {
        return score() < WIN_SCORE
    }

    fun gameResult(dealerPoint: Int, targetGap: Int) {
        if (gapWinScore() == targetGap) gameResult = GameResult.WIN
        if (dealerPoint > WIN_SCORE) gameResult = GameResult.LOSE
        if (dealerPoint > WIN_SCORE && gapWinScore() == targetGap) gameResult = GameResult.WIN
    }

    fun gapWinScore(): Int {
        return abs(WIN_SCORE - score())
    }

    fun cardToString(): String {
        return cards.toString()
    }
}
