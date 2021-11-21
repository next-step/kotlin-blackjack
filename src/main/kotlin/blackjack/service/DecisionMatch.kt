package blackjack.service

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

class DecisionMatch {

    fun matchWhenFirstCardBlackjack(dealer: Dealer, players: List<Player>) {
        val isDealerBlackjack = dealer.isBlackjack()

        players.forEach { player ->
            val isPlayerBlackjack = player.isBlackjack()

            if (isPlayerBlackjack && !isDealerBlackjack) {
                val revenue = player.betAmount / HALF
                player.addRevenue(revenue)
                dealer.minusRevenue(revenue)
            } else if (!isPlayerBlackjack && isDealerBlackjack) {
                val revenue = player.betAmount

                player.minusRevenue(revenue)
                dealer.addRevenue(revenue)
            }
        }
    }

    fun match(dealer: Dealer, players: List<Player>) {
        val diePlayers = players.filter { player -> player.isOverDeadline() }
        val alivePlayers = players - diePlayers

        diePlayers(dealer, diePlayers)

        if (dealer.isOverDeadline()) {
            allLose(dealer, alivePlayers)
            return
        }

        determineWinOrLose(dealer, alivePlayers)
    }

    private fun diePlayers(
        dealer: Dealer,
        diePlayers: List<Player>,
    ) {
        diePlayers.forEach { player ->
            val betAmount = player.betAmount
            player.minusRevenue(betAmount)
            dealer.addRevenue(betAmount)
        }
    }

    private fun allLose(
        dealer: Dealer,
        players: List<Player>,
    ) {
        players.forEach { player ->
            val betAmount = player.betAmount
            player.addRevenue(betAmount)
            dealer.minusRevenue(betAmount)
        }
    }

    private fun determineWinOrLose(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val dealerCardSum = dealer.getCardSum()

        players.forEach { player ->
            val playerCardSum = player.getCardSum()
            val betAmount = player.betAmount

            if (playerCardSum > dealerCardSum) {
                dealer.minusRevenue(betAmount)
                player.addRevenue(betAmount)
            } else if (playerCardSum < dealerCardSum) {
                dealer.addRevenue(betAmount)
                player.minusRevenue(betAmount)
            }
        }
    }

    companion object {
        private const val HALF = 2
    }
}
