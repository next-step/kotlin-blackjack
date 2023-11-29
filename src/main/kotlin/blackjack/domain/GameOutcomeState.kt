package blackjack.domain

enum class GameOutcome {
    WIN, LOSE
}

sealed class GameOutcomeState {
    abstract fun calculateOutcome(player: Player, dealer: Dealer): GameOutcome

    companion object {
        fun create(participant: Participant): GameOutcomeState {
            return when (participant) {
                is Player -> PlayerWinState
                is Dealer -> DealerWinState
            }
        }
    }
}

object PlayerWinState : GameOutcomeState() {
    override fun calculateOutcome(player: Player, dealer: Dealer): GameOutcome {
        val playerScore = player.getScore()
        val dealerScore = dealer.getScore()

        if (dealerScore > BLACKJACK) {
            return GameOutcome.WIN
        }

        if ((playerScore > BLACKJACK) || (dealerScore in playerScore..BLACKJACK)) {
            return GameOutcome.LOSE
        }

        return GameOutcome.WIN
    }
}

object DealerWinState : GameOutcomeState() {
    override fun calculateOutcome(player: Player, dealer: Dealer): GameOutcome {
        val playerScore = player.getScore()
        val dealerScore = dealer.getScore()

        if (dealerScore > BLACKJACK || (playerScore in (dealerScore + 1)..BLACKJACK)) {
            return GameOutcome.LOSE
        }

        return GameOutcome.WIN
    }
}
