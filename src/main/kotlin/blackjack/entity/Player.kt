package blackjack.entity

class Player(
    name: String,
    bettingAmount: BettingAmount,
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

    fun calculateResult(dealerScore: Int): GameResult {
        val playerScore = calculateScore()

        return calculateGameResult(playerScore, dealerScore)
    }

    private fun calculateGameResult(
        playerScore: Int,
        dealerScore: Int,
    ): GameResult {
        return when {
            isBusted() -> handlePlayerBust(dealerScore)
            dealerScore > BLACKJACK -> GameResult(this, wins = 1)
            else -> compareScores(playerScore, dealerScore)
        }
    }

    private fun handlePlayerBust(dealerScore: Int): GameResult {
        return if (dealerScore > BLACKJACK) {
            GameResult(this, draws = 1)
        } else {
            GameResult(this, loses = 1)
        }
    }

    private fun compareScores(
        playerScore: Int,
        dealerScore: Int,
    ): GameResult {
        val playerDistance = closeToBlackjack(playerScore)
        val dealerDistance = closeToBlackjack(dealerScore)

        return when {
            playerDistance < dealerDistance -> GameResult(this, wins = 1)
            playerDistance > dealerDistance -> GameResult(this, loses = 1)
            else -> GameResult(this, draws = 1)
        }
    }
}
