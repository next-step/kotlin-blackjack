package blackjack

class DefaultBlackJackJudgeOutcomeStrategy : BlackJackJudgeOutcomeStrategy {
    override fun judgeOutcome(
        dealer: Dealer,
        player: Player,
    ): Outcome {
        return when {
            player.isBust() -> Outcome.LOSS
            dealer.isBust() -> Outcome.WIN
            dealer.isBlackJack() && player.isBlackJack() -> Outcome.PUSH
            dealer.isBlackJack() -> Outcome.LOSS
            player.isBlackJack() -> Outcome.BLACKJACK
            player.sumOfHand() > dealer.sumOfHand() -> Outcome.WIN
            player.sumOfHand() < dealer.sumOfHand() -> Outcome.LOSS
            else -> Outcome.PUSH
        }
    }
}
