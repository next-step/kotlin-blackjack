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

    fun gameResult(dealer: Dealer, targetGap: Int) {
        if (this is Dealer) {
            dealerResult()
            return
        }
        playerResult(dealer, targetGap)
    }

    private fun dealerResult() {
        if (score() >= WIN_SCORE) gameResult = GameResult.WIN
    }

    private fun playerResult(dealer: Dealer, targetGap: Int) {
        if (dealer.gameResult == GameResult.WIN) {
            gameResult = GameResult.LOSE
            return
        }
        if ((gapWinScore() == targetGap) || (score() == WIN_SCORE)) gameResult = GameResult.WIN
    }

    fun gapWinScore(): Int {
        return abs(WIN_SCORE - score())
    }

    fun cardToString(): String {
        return cards.toString()
    }
}
