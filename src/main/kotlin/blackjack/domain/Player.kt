package blackjack.domain

import blackjack.BlackJackGame
import blackjack.GameResult
import blackjack.domain.card.Card
import blackjack.domain.card.Cards

open class Player(
    val name: String,
    val cards: Cards = Cards()
) {
    lateinit var gameResult: GameResult

    override fun toString(): String {
        return name
    }

    open fun addCard(card: Card) {
        cards.addCard(card)
    }

    fun getScore(): Int {
        return cards.getScore()
    }

    open fun isReceivableNewCard(): Boolean {
        return getScore() < BlackJackGame.MAX_SCORE
    }

    open fun setGameResult(win: Boolean) {
        gameResult = if (win) GameResult.WIN else GameResult.LOSE
    }
}
