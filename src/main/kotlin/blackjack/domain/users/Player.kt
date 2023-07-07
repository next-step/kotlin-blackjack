package blackjack.domain.users

import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.result.PlayerResult
import blackjack.view.printUserCards

class Player(
    name: String,
    cards: Cards,
    private var isDeckComplete: Boolean = false
) : User(name, cards) {
    fun deckComplete() {
        isDeckComplete = true
    }

    fun isDeckInComplete(): Boolean {
        return !isDeckComplete
    }

    fun userCardReceive(card: Card) {
        plusCard(card)

        if (cards.value() >= BlackjackGame.BLACKJACK_VALUE) {
            deckComplete()
        }
    }
}
