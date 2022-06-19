package blackjack.domain

data class BlackjackParticipantResult(
    val name: PlayerName,
    val hands: Hands,
    val matchStatus: MatchStatus
)
