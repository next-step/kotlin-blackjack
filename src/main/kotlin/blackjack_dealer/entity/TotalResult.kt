package blackjack_dealer.entity

data class TotalResult(
    val dealerResult: DealerResult,
    val participantsResult: List<ParticipantResult>,
)
