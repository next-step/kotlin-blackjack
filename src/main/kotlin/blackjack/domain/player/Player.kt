package blackjack.domain.player

import blackjack.domain.GameResultState
import blackjack.domain.card.Card
import blackjack.domain.card.Cards

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
        gameResultState = cards.match(dealerScore)
        return gameResultState
    }
}
