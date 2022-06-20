package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

class WinnerJudge(private val players: List<Player>, private val dealer: Dealer) {

    init {
        calculateWinner()
    }

    private fun calculateWinner() {
        players.forEach {
            judge(it, dealer)
        }
    }

    private fun judge(player: Player, dealer: Dealer) {
        if (checkPlayerWin(player, dealer)) {
            player.gambleSummary.isWinner = true
            dealer.lose++
        }

        if (checkDealerWin(player, dealer)) {
            player.gambleSummary.isWinner = false
            dealer.win++
        }

        adjustLossBattingAmount(player)
        adjustEarningRate(player, dealer)
    }

    private fun adjustEarningRate(player: Player, dealer: Dealer) {
        if (conditionOfFirstBlackJack(player, dealer)) {
            player.gambleSummary.earningRate = FIRST_BLACKJACK_EARNING_RATE
        }
    }

    private fun conditionOfFirstBlackJack(player: Player, dealer: Dealer): Boolean {
        return player.receivedCards.count() == CARD_SIZE_FOR_FIRST_BLACKJACK_POLICY && player.isBlackJack() && !dealer.isBlackJack()
    }

    private fun adjustLossBattingAmount(gamer: Player) {
        if (gamer.isBust() || !gamer.gambleSummary.isWinner) {
            gamer.adjustBustBattingAmount()
        }
    }

    private fun checkPlayerWin(player: Player, dealer: Dealer): Boolean {
        if (player.isBust()) {
            return false
        }

        return dealer.score > BLACKJACK_SCORE || player.score > dealer.score || player.score == BLACKJACK_SCORE
    }

    private fun checkDealerWin(player: Player, dealer: Dealer): Boolean {
        if (dealer.isBust()) {
            return false
        }

        return player.score > BLACKJACK_SCORE || player.score < dealer.score || dealer.score == BLACKJACK_SCORE
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val CARD_SIZE_FOR_FIRST_BLACKJACK_POLICY = 2
        private const val FIRST_BLACKJACK_EARNING_RATE = 1.5
    }
}
