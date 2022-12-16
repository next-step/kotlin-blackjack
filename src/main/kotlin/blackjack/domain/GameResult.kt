package blackjack.domain

data class GameResult(
    val dealerName: String,
    val participantResult: List<ParticipantResult>,
)
