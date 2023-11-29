package blackjack_dealer.entity.result

data class TotalResult(
    val dealerResult: DealerResult,
    val participantsResult: List<ParticipantResult>,
)
