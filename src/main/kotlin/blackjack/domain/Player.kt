package blackjack.domain

import kotlin.math.abs

open class Player(open val name: PlayerName) {

    var gameResultState: GameResultState = GameResultState.DRAW

    val cards: Cards = Cards(mutableSetOf())

    fun addCard(card: Card?) {
        if (card != null) {
            cards.addCard(card)
        }
    }

    open fun getScore(): Int {
        return cards.getCardScore()
    }

    fun matchGameScore(dealerScore: Int): GameResultState {
        var gameResult = GameResultState.DRAW
        val dealerGap = abs(Cards.WIN_SCORE - dealerScore)
        val playerGap = abs(Cards.WIN_SCORE - getScore())
        if (dealerGap < playerGap) {
            gameResult = GameResultState.LOSE
        }
        if (dealerGap > playerGap) {
            gameResult = GameResultState.WIN
        }
        gameResultState = gameResult
        return gameResult
    }
}
