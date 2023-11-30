package blackjack.domain

import blackjack.domain.player.Player

class Dealer(
    private val deck: Deck
) {

    fun handCard(player: Player) {
        player.handCard(deck.draw())
    }
}
