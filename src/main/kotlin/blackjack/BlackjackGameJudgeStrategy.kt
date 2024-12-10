package blackjack

interface BlackjackGameJudgeStrategy {
    fun evaluatePlayerResult(
        dealerState: State,
        playerState: State,
    ): PlayerResult
}
