package blackjack

object Rule {
    fun getWinner(dealer: Dealer, player: Player): Player? {
        val dealerSum = dealer.sum
        val playerSum = player.sum

        if (isOverBlackJack(playerSum)) return dealer

        if (isOverBlackJack(dealerSum)) return player

        return if (dealerSum > playerSum) dealer
        else if (dealerSum < playerSum) player
        else null
    }

    private fun isOverBlackJack(score: Int): Boolean = score > BLACK_JACK

    const val DEALER_MINIMUM_SCORE = 17
    const val BLACK_JACK = 21
}
