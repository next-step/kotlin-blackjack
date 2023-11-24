package blackjack

data class GameParticipants(
    val players: List<GameParticipantPlayer>,
    val dealer: GameParticipantDealer
) {
    val participants = players + dealer

    fun calcMatchResult(): GameParticipantResults {
        return GameParticipantResults(compareScore())
    }

    private fun compareScore(): List<GameParticipantPlayerResult> {
        if (dealer.isBust) return players.map {
            GameParticipantPlayerResult(
                it.name,
                MatchResult.WIN
            )
        }

        return players.map {
            if (it.isBust) GameParticipantPlayerResult(it.name, MatchResult.LOSS)
            else if (it.isBlackjack()) GameParticipantPlayerResult(it.name, MatchResult.WIN)
            else GameParticipantPlayerResult(it.name, MatchResult.of(it, dealer))
        }
    }
}
