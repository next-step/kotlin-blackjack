package blackjack

import blackjack.dealer.Dealer
import blackjack.player.Player

class BlackJackGame(
    val players: List<Player>,
    private val dealer: Dealer
) {

    fun startGame() {
        giveStartCards()
    }

    private fun giveStartCards() {
        players.forEach { dealer.giveCards(it, INIT_DRAW_CARD_COUNT) }
    }

    fun ask(player: Player, needMoreCard: Boolean) {
        require(player in players)
        if (needMoreCard) {
            giveCard(player)
        } else {
            stopPlayerBetting(player)
        }
    }

    private fun giveCard(player: Player) {
        dealer.giveCard(player)
    }

    private fun stopPlayerBetting(player: Player) {
        player.stopBetting()
    }

    fun getPlayablePlayer(): Player? {
        return this.players
            .filterNot { it.bust }
            .firstOrNull { it.wantToPlay() }
    }

    companion object {
        private const val INIT_DRAW_CARD_COUNT = 2
    }
}
