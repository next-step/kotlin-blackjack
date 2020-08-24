package blackjack.model

import kotlin.math.abs

const val WIN_SCORE = 21

open class Player(val name: String, private val cards: Cards = Cards(emptyList())) {
    var gameResult: GameResult = GameResult.DRAW
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

    fun gapWinScore(): Int {
        return abs(WIN_SCORE - score())
    }

    fun cardToString(): String {
        return cards.toString()
    }

    fun gameResult(dealer: Dealer, targetGap: Int) {
        if (this !is Dealer) {
            playerResult(dealer, targetGap)
        }
    }

    fun isWinner(gapScore: Int): Boolean {
        return gapScore == gapWinScore()
    }

    fun isOverWinScore(): Boolean {
        return score() > WIN_SCORE
    }

    private fun playerResult(dealer: Dealer, gapPoint: Int) {
        this.gameResult = when {
            dealer.isOverWinScore() -> GameResult.LOSE
            dealer.isWinner(gapPoint) && isWinner(gapPoint) -> GameResult.DRAW
            !dealer.isWinner(gapPoint) && isWinner(gapPoint) -> GameResult.WIN
            else -> GameResult.LOSE
        }
    }
}
