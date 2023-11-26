package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.state.State
import blackjack.model.state.playState.gameState.Bust
import blackjack.model.state.playState.gameState.Hit

data class Player(val name: String, var state: State) {
    constructor(name: String, cards: CardDeck = CardDeck()) : this(name, Hit(cards))

    fun draw(card: Card) {
        this.state = state.draw(card)
    }

    fun stay() {
        this.state = state.stay()
    }

    fun isFinished() = state.isFinished()

    fun isNotBust() = state !is Bust

    fun cards(): List<Card> {
        return state.cards()
    }

    fun score() = state.calculateScore()
}
