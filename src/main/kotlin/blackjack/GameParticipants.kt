package blackjack

data class GameParticipants(
    val players: List<GameParticipantPlayer>,
    val dealer: GameParticipantDealer
) {
    val participants = players + dealer

    fun calcMatchResult(): GameParticipantResults {
        return GameParticipantResults(compareScore())
    }

    private fun compareScore(): List<GameParticipantPlayerResult> =
        players.map {
            GameParticipantPlayerResult(it.name, MatchResult.of(it, dealer), it.betAmount)
        }
}
