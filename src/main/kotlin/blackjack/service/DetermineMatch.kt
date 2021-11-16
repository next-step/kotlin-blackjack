package blackjack.service

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.ResultStatus

class DetermineMatch {

    fun match(dealer: Dealer, players: List<Player>) {
        val dealerCardSum = dealer.getCardSum()

        if (dealerCardSum > DEADLINE) {
            allLose(dealer, players)
            return
        }

        determineWinOrLose(
            dealer,
            players,
            dealerCardSum,
        )
    }

    private fun allLose(
        dealer: Dealer,
        players: List<Player>,
    ) {
        players.forEach { player ->
            dealer.determineWinOrLose(ResultStatus.LOSE)
            player.determineWinOrLose(ResultStatus.WIN)
        }
    }

    private fun determineWinOrLose(
        dealer: Dealer,
        players: List<Player>,
        dealerCardSum: Int,
    ) {
        players.forEach { player ->
            val playerCardSum = player.getCardSum()

            if (playerCardSum > dealerCardSum) {
                dealer.determineWinOrLose(ResultStatus.LOSE)
                player.determineWinOrLose(ResultStatus.WIN)
            } else if (playerCardSum < dealerCardSum) {
                dealer.determineWinOrLose(ResultStatus.WIN)
                player.determineWinOrLose(ResultStatus.LOSE)
            } else {
                dealer.determineWinOrLose(ResultStatus.TIE)
                player.determineWinOrLose(ResultStatus.TIE)
            }
        }
    }

    companion object {
        private const val DEADLINE = 21
    }
}
