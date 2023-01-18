package blackjack.domains.participants

import blackjack.GameRule.Companion.BLACKJACK
import blackjack.GameRule.Companion.DEALER_DRAW_CONDITION
import blackjack.GameScoreType
import blackjack.domains.deck.Cards
import blackjack.views.Output.printDealerDraw

class Dealer(
    override val name: String = "딜러", override val cards: Cards = Cards()
) : User(name, cards) {

    private val gameScores = mutableListOf<GameScoreType>()

    override fun isDrawable(): Boolean {
        if (!cards.isDrawable(DEALER_DRAW_CONDITION)) return false
        return true
    }

    override fun printHasCards() {
        printDealerDraw()
    }

    override fun win() {
        gameScores.add(GameScoreType.WIN)
    }

    override fun lose() {
        gameScores.add(GameScoreType.LOSE)
    }

    override fun draw() {
        gameScores.add(GameScoreType.DRAW)
    }

    fun isOver21(): Boolean {
        return summedCardNumbers > BLACKJACK
    }

    fun calculatePlayerResult(playerScore: Int): GameScoreType {
        if (summedCardNumbers < playerScore) return GameScoreType.WIN
        if (summedCardNumbers > playerScore) return GameScoreType.LOSE
        return GameScoreType.DRAW
    }

    fun getScoreCounts(scoreType: GameScoreType): Int {
        return gameScores.filter { it == scoreType }.size
    }
}
