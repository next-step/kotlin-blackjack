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
        dealer.drawTo(INIT_DRAW_CARD_COUNT, *players.toTypedArray())
    }

    fun ask(player: Player, needMoreCard: Boolean) {
        require(player in players)
        if (needMoreCard) {
            dealer.drawTo(BASE_DRAW_CARD_COUNT, player)
        } else {
            stopPlayerBetting(player)
        }
    }

    private fun stopPlayerBetting(player: Player) {
        player.stopBetting()
    }

    fun getPlayablePlayer(): Player? {
        return this.players
            .filterNot { it.isBust() }
            .firstOrNull { it.wantToPlay() }
    }

    companion object {
        private const val INIT_DRAW_CARD_COUNT = 2
        private const val BASE_DRAW_CARD_COUNT = 1
    }
}
