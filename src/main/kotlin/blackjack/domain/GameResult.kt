package blackjack.domain

class GameResult(private val dealer: Dealer, private val players: List<Player>) {
    fun getMatchCount(resultMap: List<Map<Player, MatchResult>>): List<Map<MatchResult, Int>> {
        val countMapList = resultMap.map { playerMap ->
            val countMap = playerMap
                .values
                .groupingBy { it }
                .eachCount()
                .map { (matchResult, count) -> matchResult to count }
                .toMap()

            countMap
        }

        return countMapList
    }

    fun getResultMap(): List<Map<Player, MatchResult>> {
        var resultMap: MutableList<Map<Player, MatchResult>> = mutableListOf()
        for (player in players) {
            val playerMap = mapOf(player to dealer.compare(player))
            resultMap.add(playerMap)
        }
        return resultMap
    }
}
