package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.state.State
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

    fun isBust() = state.isBust()

    fun isBlackJack() = state.isBlackJack()

    fun isStay() = state.isStay()

    fun cards(): List<Card> {
        return state.cards()
    }

    fun score() = state.calculateScore()
}
