package blackjack

class GameScoreCalculator(
    private val gameParticipants: GameParticipants
) {

    fun getMatchResult(): GameParticipantResults {
        val (players, dealer) = gameParticipants.let { it.players to it.dealer }
        if(dealer.isBust) {
            val playersResult = players.map { GameParticipantResult.Player(it.name, MatchResult.WIN) }
            val dealerResult = GameParticipantResult.Dealer(dealer.name, playersResult.map { MatchResult.LOSS })
            return GameParticipantResults(playersResult, dealerResult)
        }

        return compareScore(players, dealer)
    }

    private fun compareScore(players: List<GameParticipant>, dealer: GameParticipant): GameParticipantResults {
        val dealerScore = dealer.getScore()
        val playersResult = players.map { player ->
            val matchResult = MatchResult.of(player.getScore(), dealerScore)
            GameParticipantResult.Player(player.name, matchResult)
        }
        val dealerMatchResult = GameParticipantResult.Dealer(dealer.name, playersResult.map { MatchResult.reverse(it.matchResult) })
        return GameParticipantResults(playersResult, dealerMatchResult)
    }
}
