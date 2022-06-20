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

    fun allPlayer(): List<Player> {
        return players
    }

    fun playerCards(player: Player) {
        players.first { it == player }.show()
    }

    private fun giveStartCards() {
        players.forEach { dealer.giveCards(it, INIT_DRAW_CARD_COUNT) }
    }

    companion object {
        private const val INIT_DRAW_CARD_COUNT = 2
    }
}
