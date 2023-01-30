package blackjack

import blackjack.GameRule.Companion.BLACKJACK
import blackjack.domains.participants.Dealer
import blackjack.domains.participants.Gamers
import blackjack.domains.participants.Player
import kotlin.math.roundToInt

class ResultCalculator(private val users: Gamers) {

    fun setUserRanks() {
        val dealer = users.getDealer()
        val players = users.getPlayers()
        if (dealer.isOverBlackjack()) {
            players.forEach { player -> playerWin(dealer, player) }
            return
        }
        players.forEach { player ->
            if (player.summedCardNumbers > BLACKJACK) {
                return@forEach playerLose(dealer, player)
            }
            when (dealer.calculatePlayerResult(player.summedCardNumbers)) {
                GameScoreType.WIN -> playerWin(dealer, player)
                GameScoreType.LOSE -> playerLose(dealer, player)
                GameScoreType.DRAW -> playerDraw(dealer, player)
            }
        }
    }

    private fun playerWin(dealer: Dealer, player: Player) {
        val twoCardsWithBlackjack = player.cards.getCardSize() == 2 && player.cards.isBlackJack()
        val playerBettingAmount = if (twoCardsWithBlackjack) {
            (player.bettingAmount * 1.5).roundToInt()
        } else {
            player.bettingAmount
        }

        player.calcEarningAmount(playerBettingAmount)
        dealer.calcEarningAmount(-playerBettingAmount)
        player.win()
        dealer.lose()
    }

    private fun playerDraw(dealer: Dealer, player: Player) {
        player.draw()
        dealer.draw()
    }

    private fun playerLose(dealer: Dealer, player: Player) {
        val playerBettingAmount = player.bettingAmount
        player.calcEarningAmount(-playerBettingAmount)
        dealer.calcEarningAmount(playerBettingAmount)
        player.lose()
        dealer.win()
    }
}
