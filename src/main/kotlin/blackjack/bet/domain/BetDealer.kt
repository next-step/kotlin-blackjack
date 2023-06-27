package blackjack.bet.domain

import blackjack.bet.view.BetPlayerStatus
import blackjack.common.service.DeckManager

class BetDealer : BetPlayer(name = "딜러") {
    fun beginRound(deckManager: DeckManager, players: List<BetPlayer>) {
        this.hit(*deckManager.drawTwoCards())
        for (player in players) {
            player.hit(*deckManager.drawTwoCards())
        }
    }

    fun getParticipantInitialStatus(players: Array<BetPlayer>): List<BetPlayerStatus> {
        val scorePlayerStatuses = mutableListOf(BetPlayerStatus.dealerUpCard(this))
        for (player in players) {
            scorePlayerStatuses.add(BetPlayerStatus.of(player))
        }

        return scorePlayerStatuses
    }

    fun drawCardIfNeeded(deckManager: DeckManager, handNotice: (BetPlayer) -> Unit) {
        while (canDraw()) {
            drawPhase(deckManager = deckManager, handNotice = handNotice)
        }
    }

    override fun canDraw(): Boolean {
        return this.optimalValue() <= STAND_THRESHOLD
    }

    companion object {
        private const val STAND_THRESHOLD = 16
    }
}
