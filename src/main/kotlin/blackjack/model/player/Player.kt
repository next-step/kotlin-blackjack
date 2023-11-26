package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.state.State
import blackjack.model.state.playState.gameState.BlackJack
import blackjack.model.state.playState.gameState.Bust
import blackjack.model.state.playState.gameState.Hit

data class Player(val name: String, val bettingAmount: Double = 0.0, var state: State) {
    constructor(name: String, bettingAmount: Double = 0.0, cards: CardDeck = CardDeck()) :
        this(name, bettingAmount, Hit(cards))

    fun draw(card: Card) {
        this.state = state.draw(card)
    }

    fun stay() {
        this.state = state.stay()
    }

    fun isFinished() = state.isFinished()

    fun isNotBust() = state !is Bust

    fun isBlackJack() = state is BlackJack

    fun cards(): List<Card> {
        return state.cards()
    }

    fun score() = state.calculateScore()
}
