package blackjack.domain.player

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck

class Player(val name: String, deck: Deck) {
    private var _status: Status = Start(Cards(listOf())).draw(deck)
    val status: Status
        get() {
            return _status
        }

    fun action(isDraw: Boolean, deck: Deck) {
        if (isDraw) {
            updateCards { _status.draw(deck) }
        } else {
            updateCards { _status.stay() }
        }
    }

    private fun updateCards(action: () -> Status) {
        _status = action()
    }
}
