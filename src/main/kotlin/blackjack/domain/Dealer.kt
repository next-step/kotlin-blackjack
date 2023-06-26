package blackjack.domain

import blackjack.service.DeckManager
import blackjack.view.PlayerStatus

class Dealer : Player(name = "딜러") {

    fun beginRound(deckManager: DeckManager, players: Array<Player>) {
        this.hit(*deckManager.drawTwoCards())
        for (player in players) {
            player.hit(*deckManager.drawTwoCards())
        }
    }

    fun getParticipantInitialStatus(players: Array<Player>): List<PlayerStatus> {
        val playerStatus = mutableListOf(PlayerStatus.dealerUpCard(this))
        for (player in players) {
            playerStatus.add(PlayerStatus.of(player))
        }

        return playerStatus
    }

    fun needToDraw(): Boolean {
        return this.optimalValue() <= STAND_THRESHOLD
    }

    companion object {
        private const val STAND_THRESHOLD = 16
    }
}
