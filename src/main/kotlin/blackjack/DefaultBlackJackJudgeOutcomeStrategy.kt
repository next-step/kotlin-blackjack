package blackjack

class DefaultBlackJackJudgeOutcomeStrategy : BlackJackJudgeOutcomeStrategy {
    override fun judgeOutcome(
        dealer: Dealer,
        player: Player,
    ): Outcome {
        if (player.isBust()) {
            return Outcome.LOSS
        }
        if (dealer.isBust()) {
            return Outcome.WIN
        }

        return when {
            player.sumOfHand() > dealer.sumOfHand() -> Outcome.WIN
            player.sumOfHand() < dealer.sumOfHand() -> Outcome.LOSS
            else -> Outcome.DRAW
        }
    }
}
