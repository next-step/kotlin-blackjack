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
        if (checkDraw(player, dealer)) {
            player.gambleSummary.gameStatus = GameStatus.DRAW
        }

        if (checkPlayerWin(player, dealer)) {
            player.gambleSummary.gameStatus = GameStatus.WIN
            dealer.lose++
        }

        if (checkDealerWin(player, dealer)) {
            player.gambleSummary.gameStatus = GameStatus.LOSE
            dealer.win++
        }

        adjustLossBattingAmount(player)
        adjustEarningRate(player, dealer)
    }

    private fun adjustEarningRate(player: Player, dealer: Dealer) {
        if (conditionOfBlackJack(player, dealer)) {
            player.gambleSummary.earningRate = FIRST_BLACKJACK_EARNING_RATE
        }
    }

    private fun conditionOfBlackJack(player: Player, dealer: Dealer): Boolean {
        return player.isBlackJack() && !dealer.isBlackJack()
    }

    private fun adjustLossBattingAmount(gamer: Player) {
        if (gamer.isBust() || gamer.gambleSummary.gameStatus == GameStatus.LOSE) {
            gamer.adjustBustBattingAmount()
        }
    }

    private fun checkDraw(player: Player, dealer: Dealer): Boolean {
        return player.score == dealer.score
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
        private const val FIRST_BLACKJACK_EARNING_RATE = 1.5
    }
}
