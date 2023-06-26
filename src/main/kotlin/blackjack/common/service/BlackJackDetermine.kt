package blackjack.common.service

object BlackJackDetermine {
    fun determineWinner(playerValue: Int, dealerValue: Int): Winner {
        return when {
            playerValue > 21 -> Winner.DEALER
            dealerValue > 21 -> Winner.DEALER_BUST
            playerValue > dealerValue -> Winner.PLAYER
            playerValue < dealerValue -> Winner.DEALER
            else -> Winner.DRAW
        }
    }
    enum class Winner {
        PLAYER, DEALER, DRAW, DEALER_BUST
    }
}
