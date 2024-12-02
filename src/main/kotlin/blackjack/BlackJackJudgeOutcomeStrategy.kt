package blackjack

interface BlackJackJudgeOutcomeStrategy {
    fun judgeOutcome(
        dealer: Dealer,
        player: Player,
    ): Outcome
}
