package blackjack.step2.domain

data class GameResult(
    val participant: Participant,
    val resultTypes: List<GameResultType>,
)
