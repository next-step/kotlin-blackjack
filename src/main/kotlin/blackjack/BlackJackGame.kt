package blackjack

import blackjack.dealer.Dealer
import blackjack.player.Player

class BlackJackGame(
    private val players: List<Player>,
    private val dealer: Dealer
) {

    fun startGame() {
        giveStartCards()
    }

    fun showPlayersCards(player: Player) {
        players.first { it == player }.show()
    }

    private fun giveStartCards() {
        players.forEach { dealer.giveCard(it) }
    }
}
