package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck

class Dealer {
    val cardDeck: CardDeck = CardDeck()

    fun draw(): Card {
        return cardDeck.draw()
    }

    fun deal(player: Player) {
        player.addCard(draw())
    }
}
