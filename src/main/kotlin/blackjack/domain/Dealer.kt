package blackjack.domain

import blackjack.DeckManager
import blackjack.GameResultChecker
import blackjack.view.PlayerStatus

class Dealer : Player(name = "딜러") {

    fun initializeRound(deckManager: DeckManager, players: Array<Player>) {
        this.hit(*deckManager.drawTwoCards())
        for (player in players) {
            player.hit(*deckManager.drawTwoCards())
        }
    }

    fun getAllParticipantsStatus(players: Array<Player>): List<PlayerStatus> {
        val playerStatus = mutableListOf(PlayerStatus.of(this))
        for (player in players) {
            playerStatus.add(PlayerStatus.of(player))
        }

        return playerStatus
    }

    fun drawAdditionalCard(deckManager: DeckManager, dealerAddNotice: () -> Unit) {
        if (this.optimalValue() <= DEALER_MIN_VALUE_TO_DRAW) {
            this.hit(deckManager.draw())
            dealerAddNotice.invoke()
        }
    }

    override fun gameResult(): String {
        return scoreBoard().resultForDealer()
    }

    fun determineWinner(players: Array<Player>, checker: GameResultChecker) {
        players.forEach {
            val playerValue = it.optimalValue()
            val dealerValue = this.optimalValue()
            val winner = checker.determineWinner(playerValue, dealerValue)
            checker.updateScores(winner, it)
        }
    }

    companion object {
        private const val DEALER_MIN_VALUE_TO_DRAW = 16
    }
}
