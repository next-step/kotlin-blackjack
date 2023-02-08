package blackjack.domains.participants

import blackjack.GameScoreType
import blackjack.domains.GameRule.Companion.BLACKJACK
import blackjack.domains.deck.Cards
import blackjack.views.Input.answerDrawCard

class Player(
    override val name: String,
    override val cards: Cards = Cards(),
) : User(name, cards) {
    var bettingAmount: Int = 0
        private set

    lateinit var gameScore: GameScoreType
        private set

    override fun isDrawable(): Boolean {
        if (!cards.isDrawable(BLACKJACK)) return false
        if (!answerDrawCard(name)) return false
        return true
    }

    override fun win() {
        gameScore = GameScoreType.WIN
    }

    override fun lose() {
        gameScore = GameScoreType.LOSE
    }

    override fun draw() {
        gameScore = GameScoreType.DRAW
    }

    override fun calcEarningAmount(earningAmount: Int) {
        this.earningAmount = earningAmount
    }

    fun makeABet(bettingAmount: Int) {
        this.bettingAmount = bettingAmount
    }
}
