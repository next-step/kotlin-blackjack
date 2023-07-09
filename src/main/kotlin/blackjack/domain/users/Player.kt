package blackjack.domain.users

import blackjack.domain.BlackjackGame
import blackjack.domain.Card
import blackjack.domain.Cards

class Player(
    name: String,
    cards: Cards,
    val bettingAmount: Int
) : User(name, cards) {
    fun isDeckInComplete(): Boolean {
        return cards.value() < BlackjackGame.BLACKJACK_VALUE
    }

    fun userCardReceive(card: Card) {
        plusCard(card)
    }
}
