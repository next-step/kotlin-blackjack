package blackjack.domain

data class BlackjackResult(
    val name: PlayerName,
    val hands: Hands,
    val matchStatus: MatchStatus
)
