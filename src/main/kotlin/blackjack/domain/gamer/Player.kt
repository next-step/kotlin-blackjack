package blackjack.domain.gamer

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.exception.InvalidPlayerNameException

class Player(
    override val name: String,
    override val cards: Cards = Cards(),
) : Gamer {

    init {
        if (name.isEmpty()) {
            throw InvalidPlayerNameException()
        }
    }

    override fun receiveCard(card: Card) {
        cards.receiveCard(card)
    }

    override fun haveCards(): String = cards.haveCards()
}
