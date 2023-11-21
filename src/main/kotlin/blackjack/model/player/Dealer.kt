package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.state.State
import blackjack.model.state.playState.gameState.Hit

class Dealer(val name: String, val cards: CardDeck = CardDeck()) {
    private var state: State = Hit(cards)

    fun draw(card: Card) {
        this.state = state.draw(card)
    }

    fun stay() {
        this.state = state.stay()
    }

    fun cards(): List<Card> {
        return state.cards().deck
    }
}
