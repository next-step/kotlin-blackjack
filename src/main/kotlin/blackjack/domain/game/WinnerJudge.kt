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
        if (player.isDraw(dealer)) {
            player.gambleSummary.gameStatus = GameStatus.DRAW
        }

        if (player.isWin(dealer)) {
            player.gambleSummary.gameStatus = GameStatus.WIN
            dealer.lose++
        }

        if (dealer.isWin(player)) {
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

    companion object {
        private const val FIRST_BLACKJACK_EARNING_RATE = 1.5
    }
}
