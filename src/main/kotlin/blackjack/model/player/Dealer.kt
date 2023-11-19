package blackjack.model.player

import blackjack.model.card.CardDeck
import blackjack.model.strategy.ShuffleStrategy

class Dealer(private val cardDeck: CardDeck) {
    constructor(strategy: ShuffleStrategy) : this(cardDeck = CardDeck(strategy))

    fun receive(player: Player) {
        player.receiveCard(cardDeck.draw())
    }
}
