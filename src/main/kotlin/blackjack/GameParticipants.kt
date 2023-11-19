package blackjack

data class GameParticipants(
    val players: List<GameParticipant>,
    val dealer: GameParticipant
) {
    val participants = players + dealer
}
