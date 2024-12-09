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
        return when {
            bothAreBlackjack(dealer) -> GameResult.draw(this)
            isBlackjack() -> GameResult.blackjackWin(this, bettingAmount)
            playerBeatsDealer(playerScore, dealer) -> GameResult.win(this, bettingAmount)
            dealerBeatsPlayer(playerScore, dealer) -> GameResult.lose(this, bettingAmount)
            else -> GameResult.draw(this)
        }
    }

    private fun bothAreBlackjack(dealer: Dealer) = isBlackjack() && dealer.isBlackjack()

    private fun playerBeatsDealer(
        playerScore: Int,
        dealer: Dealer,
    ): Boolean {
        return !isBusted() && (dealer.isBusted() || playerScore > dealer.calculateScore())
    }

    private fun dealerBeatsPlayer(
        playerScore: Int,
        dealer: Dealer,
    ): Boolean {
        return isBusted() || playerScore < dealer.calculateScore()
    }
}
