package blackjack.domain

class GameResult(private val dealer: Dealer, private val players: List<Player>) {
    private val dealerScore = dealer.getScore()

    fun getMatchCount(resultMap: List<Map<Player, MatchResult>>): List<Pair<MatchResult, Int>> {
        val countMap = resultMap
            .flatMap { it.values }
            .groupingBy { it }
            .eachCount()
            .map { (matchResult, count) -> matchResult to count }

        return countMap.toList()
    }

    fun getResultMap(): List<Map<Player, MatchResult>> {
        var resultMap: MutableList<Map<Player, MatchResult>> = mutableListOf()
        for (player in players) {
            val playerMap = mapOf(player to dealer.compare(player))
            resultMap.add(playerMap)
        }

        return resultMap
    }


    private fun Dealer.compare(player: Player): MatchResult {
        if (this.isBusted) return MatchResult.WIN
        if (player.isBusted) return MatchResult.LOSS
        val playerScore = player.getScore()
        return when {
            dealerScore == playerScore -> MatchResult.TIE
            playerScore > dealerScore -> MatchResult.WIN
            else -> MatchResult.LOSS
        }
    }

}
