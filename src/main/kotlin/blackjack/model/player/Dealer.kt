package blackjack.model.player

import blackjack.model.card.Cards
import blackjack.model.player.LOSE_TEXT as LOSE_TEXT
import blackjack.model.player.WIN_TEXT as WIN_TEXT

const val DEALER_NAME = "딜러"
const val DEALER_CALL_MAX_POINT = 16
const val DEALER_CALL_MAX_CARD = 3

class Dealer : Player(name = DEALER_NAME) {
    override fun call(): Boolean {
        return cards.sumByPoint() <= DEALER_CALL_MAX_POINT && cards.getCount() < DEALER_CALL_MAX_CARD
    }

    override fun checkWinOrLose(players: List<Player>) {
        val gamers = players.filterIsInstance<Gamer>()
        val win = gamers.count { isWin(it.cards) }
        val lose = gamers.count { !isWin(it.cards) }

        winLoseResult = "$win$WIN_TEXT$lose$LOSE_TEXT"
    }

    private fun isWin(gamerCards: Cards): Boolean {
        val dealerPoint = cards.getBlackjackPoint()
        val gamerPoint = gamerCards.getBlackjackPoint()

        if (dealerPoint > BLACKJACK_MAX_NUMBER) {
            return false
        }
        if (gamerPoint > BLACKJACK_MAX_NUMBER) {
            return true
        }
        return dealerPoint > gamerPoint
    }
}
