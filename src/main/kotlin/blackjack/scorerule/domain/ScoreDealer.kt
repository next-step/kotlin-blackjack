package blackjack.scorerule.domain

import blackjack.common.service.DeckManager
import blackjack.scorerule.view.ScorePlayerStatus

class ScoreDealer : ScorePlayer(name = "딜러") {
    fun beginRound(deckManager: DeckManager, players: Array<ScorePlayer>) {
        this.hit(*deckManager.drawTwoCards())
        for (player in players) {
            player.hit(*deckManager.drawTwoCards())
        }
    }

    fun getParticipantInitialStatus(players: Array<ScorePlayer>): List<ScorePlayerStatus> {
        val scorePlayerStatuses = mutableListOf(ScorePlayerStatus.dealerUpCard(this))
        for (player in players) {
            scorePlayerStatuses.add(ScorePlayerStatus.of(player))
        }

        return scorePlayerStatuses
    }

    fun needToDraw(): Boolean {
        return this.optimalValue() <= STAND_THRESHOLD
    }

    companion object {
        private const val STAND_THRESHOLD = 16
    }
}
