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
            isBlackjack() -> GameResult(this, earning = blackjackEarning())
            isBusted() -> GameResult(this, lossEarning())
            dealer.isBusted() -> GameResult(this, winEarning())
            playerScore > dealerScore -> GameResult(this, winEarning())
            playerScore < dealerScore -> GameResult(this, lossEarning())
            else -> GameResult(this)
        }
    }

    private fun blackjackEarning(): Int = (bettingAmount.amount * BLACKJACK_MULTIPLIER).toInt()

    private fun winEarning(): Int = bettingAmount.amount

    private fun lossEarning(): Int = -bettingAmount.amount

    companion object {
        private const val BLACKJACK_MULTIPLIER = 1.5
    }
}
