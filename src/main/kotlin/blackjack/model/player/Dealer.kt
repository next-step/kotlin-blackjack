package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.state.State
import blackjack.model.state.playState.gameState.Hit

class Dealer(val name: String, val cards: CardDeck = CardDeck()) {
    private var state: State = Hit(cards)

    fun draw(card: Card): State {
        return state.draw(card)
    }

    fun stay(): State {
        return state.stay()
    }

    fun cards(): List<Card> {
        return state.cards().deck
    }
}
