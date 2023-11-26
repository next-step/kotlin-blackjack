package blackjack

data class GameParticipants(
    val players: List<GameParticipantPlayer>,
    val dealer: GameParticipantDealer
) {
    val participants = players + dealer

    fun calcMatchResult(): GameParticipantResults {
        val gameParticipantResults = players.map { player ->
            val matchResult = compareScore(player, dealer)
            GameParticipantPlayerResult(player.name, matchResult, player.betAmount)
        }
        return GameParticipantResults(gameParticipantResults)
    }

    private fun compareScore(player: GameParticipantPlayer, dealer: GameParticipantDealer): MatchResult =
        if (player.isBust || dealer.isBlackjack()) MatchResult.LOSS
        else if (player.isBlackjack()) MatchResult.BLACKJACK
        else if (dealer.isBust) MatchResult.WIN
        else MatchResult.of(player, dealer)
}
