package blackjack.domain.dto

data class GameResult(
    val dealerName: String,
    val participantResult: List<ParticipantResult>,
)
