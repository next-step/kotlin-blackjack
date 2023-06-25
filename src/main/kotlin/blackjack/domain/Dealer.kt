package blackjack.domain

import blackjack.DeckManager
import blackjack.view.PlayerStatus

class Dealer : Player(name = "딜러") {

    fun initializeRound(deckManager: DeckManager, players: Array<Player>) {
        this.hit(*deckManager.drawTwoCards())
        if (this.optimalValue() >= STAND_THRESHOLD) this.stand()
        for (player in players) {
            player.hit(*deckManager.drawTwoCards())
        }
    }

    fun getParticipantInitialStatus(players: Array<Player>): List<PlayerStatus> {
        val playerStatus = mutableListOf(PlayerStatus.upCard(this))
        for (player in players) {
            playerStatus.add(PlayerStatus.of(player))
        }

        return playerStatus
    }

    fun getParticipantStatus(players: Array<Player>): Pair<PlayerStatus, List<PlayerStatus>> {
        val dealerStatus = PlayerStatus.of(this)
        val playerStatusList = players.map(PlayerStatus::of)
        return Pair(dealerStatus, playerStatusList)
    }

    companion object {
        private const val STAND_THRESHOLD = 17
    }
}
