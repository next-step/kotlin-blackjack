package blackjack.domain.game

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

class WinnerJudge(private val players: List<Player>) {

    init {
        calculateWinner()
    }

    private fun calculateWinner() {
        val dealer = players.filterIsInstance<Dealer>().first()
        val players = players.filter { it !is Dealer }.toList()

        players.forEach {
            judge(it, dealer)
        }
    }

    private fun judge(player: Player, dealer: Dealer) {
        if (checkPlayerWin(player, dealer)) {
            player.gamblingSummary.isWinner = true
            dealer.lose++
        }
        adjustBustBattingAmountToBust(player)

        if (checkDealerWin(player, dealer)) {
            player.gamblingSummary.isWinner = false
            dealer.win++
        }

        adjustEarningRate(player, dealer)
    }

    private fun adjustEarningRate(player: Player, dealer: Dealer) {
        if (conditionOfFirstBlackJack(player, dealer)) {
            player.gamblingSummary.earningRate = 1.5
        }
    }

    private fun conditionOfFirstBlackJack(player: Player, dealer: Dealer): Boolean {
        return player.receivedCards.count() == CARD_SIZE_FOR_FIRST_BLACKJACK_POLICY
                && player.isBlackJack()
                && !dealer.isBlackJack()
    }

    private fun adjustBustBattingAmountToBust(gamer: Player) {
        if (!gamer.isBust()) {
            return
        }

        gamer.adjustBustBattingAmount()
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
    }
}
