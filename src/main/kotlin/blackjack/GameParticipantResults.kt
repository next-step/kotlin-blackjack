package blackjack

data class GameParticipantResults(
    val players: List<GameParticipantResult.Player>,
    val dealer: GameParticipantResult.Dealer
)
