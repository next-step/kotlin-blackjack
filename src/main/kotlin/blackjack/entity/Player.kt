package blackjack.entity

class Player(
    name: String,
    val bettingAmount: BettingAmount,
) : Participant(name) {
    fun playTurn(
        deck: Deck,
        wantsToHit: Boolean,
    ): PlayerAction =
        when {
            !wantsToHit -> PlayerAction.STAND
            isBusted() -> PlayerAction.BURST
            isBlackjack() -> PlayerAction.BLACKJACK
            else -> {
                receiveCard(deck.deal())
                if (isBusted()) PlayerAction.BURST else PlayerAction.HIT
            }
        }

    fun calculateResult(dealer: Dealer): GameResult {
        val playerScore = calculateScore()

        return calculateGameResult(playerScore, dealer)
    }

    private fun calculateGameResult(
        playerScore: Int,
        dealer: Dealer,
    ): GameResult {
        val dealerScore = dealer.calculateScore()
        return when {
            isBlackjack() && dealer.isBlackjack() -> GameResult(this, earning = bettingAmount.amount)
            isBlackjack() -> GameResult(this, earning = (bettingAmount.amount * 1.5).toInt())
            isBusted() -> GameResult(this, -bettingAmount.amount)
            dealer.isBusted() -> GameResult(this, bettingAmount.amount)
            playerScore > dealerScore -> GameResult(this, bettingAmount.amount)
            playerScore < dealerScore -> GameResult(this, -bettingAmount.amount)
            else -> GameResult(this)
        }
    }
}
