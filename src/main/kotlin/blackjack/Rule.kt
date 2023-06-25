package blackjack

object Rule {
    fun decisionWinner(dealer: Dealer, player: Player): Player? {
        val dealerSum = dealer.sum
        val playerSum = player.sum

        if (isOverBlackJack(playerSum)) return dealerWin(dealer, player)

        if (isOverBlackJack(dealerSum)) return playerWin(dealer, player)

        return if (dealerSum > playerSum) dealerWin(dealer, player)
        else if (dealerSum < playerSum) playerWin(dealer, player)
        else null
    }

    private fun isOverBlackJack(score: Int): Boolean = score > BLACK_JACK

    private fun playerWin(dealer: Dealer, player: Player): Player {
        dealer.lose()
        player.win()
        return player
    }

    private fun dealerWin(dealer: Dealer, player: Player): Dealer {
        dealer.win()
        player.lose()
        return dealer
    }

    const val DEALER_MINIMUM_SCORE = 17
    const val BLACK_JACK = 21
}
