package blackjack

import blackjack.dealer.Dealer
import blackjack.player.Player

class BlackJackGame(
    private val players: List<Player>,
    private val dealer: Dealer
) {

    fun allPlayer(): List<Player> {
        return players
    }

    fun startGame() {
        giveStartCards()
    }

    private fun giveStartCards() {
        players.forEach { dealer.giveCards(it, INIT_DRAW_CARD_COUNT) }
    }

    private fun giveCard(player: Player) {
        player.getCard()
    }

    private fun Player.getCard() {
        dealer.giveCard(this)
    }

    fun getPlayablePlayer(): Player? {
        return this.players
            .filterNot { it.bust }
            .firstOrNull { it.wantToPlay() }
    }

    private fun stopPlayerBetting(player: Player) {
        player.stopBetting()
    }

    fun ask(player: Player, needMoreCard: Boolean) {
        require(player in players)
        if (needMoreCard) {
            giveCard(player)
        } else {
            stopPlayerBetting(player)
        }
    }

    companion object {
        private const val INIT_DRAW_CARD_COUNT = 2
    }
}
