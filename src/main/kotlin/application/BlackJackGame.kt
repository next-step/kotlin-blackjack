package application

import domain.player.Dealer
import domain.player.Participants
import domain.player.Playable
import domain.player.Player

class BlackJackGame(
    private val dealer: Dealer = Dealer(),
    private val players: Participants
) {
    fun init() {
        repeat(INIT_CARD_COUNT) {
            dealer.drawCard()
            players.receiveCard(dealer)
        }
    }

    fun receiveCard(playable: Playable) {
        dealer.giveCard(playable = playable)
    }

    fun dealer(): Dealer = this.dealer
    fun allPlayers(): List<Player> {
        return this.players.allPlayers()
    }

    fun playsTurn(player: Player, queryReceiveCard: (Player) -> Boolean) {
        while (player.isAvailableReceive() && queryReceiveCard(player)) {
            receiveCard(player)
        }
    }

    private fun availableReceiveCard(playable: Playable): Boolean = playable.isAvailableReceive()

    fun tryDealerReceiveCard(): Boolean {
        val availableReceiveCard = availableReceiveCard(dealer)
        if (availableReceiveCard) {
            receiveCard(dealer)
        }
        return availableReceiveCard
    }

    private companion object {
        const val INIT_CARD_COUNT = 2
    }
}
