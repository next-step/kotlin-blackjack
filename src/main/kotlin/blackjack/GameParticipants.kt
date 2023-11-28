package blackjack

data class GameParticipants(
    val players: List<GameParticipantPlayer>,
    val dealer: GameParticipantDealer
) {
    val participants = players + dealer

    fun calcMatchResult(): GameParticipantResults {
        val gameParticipantResults = players.map { player ->
            val matchResult = dealer.compareScore(player)
            GameParticipantPlayerResult(player.name, matchResult, player.betAmount)
        }
        return GameParticipantResults(gameParticipantResults)
    }
}
