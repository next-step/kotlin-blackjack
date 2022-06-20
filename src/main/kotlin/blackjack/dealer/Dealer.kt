package blackjack.dealer

import blackjack.card.Deck
import blackjack.player.Player

class Dealer(private val deck: Deck) {
    fun giveCards(player: Player, count: Int) {
        for (i in 0..count)
            giveCard(player)
    }

    fun giveCard(player: Player) {
        player.getCard(deck.draw())
    }
}
