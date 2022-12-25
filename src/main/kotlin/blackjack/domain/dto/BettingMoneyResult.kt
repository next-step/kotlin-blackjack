package blackjack.domain.dto

data class BettingMoneyResult(
    val dealerName: String,
    val participantMoneyResult: List<ParticipantMoneyResult>,
)
