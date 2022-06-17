package blackjack.dealer

import blackjack.card.Deck
import blackjack.player.Player

class Dealer(private val deck: Deck) {
    fun giveCard(player: Player) {
        player.getCard(deck.draw())
    }
}
