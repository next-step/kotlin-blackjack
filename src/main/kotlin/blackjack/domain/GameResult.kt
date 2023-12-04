package blackjack.domain

class GameResult(private val dealer: Dealer, private val players: List<Player>) {
    fun getResultMap(): List<Map<Player, MatchResult>> {
        var resultMap: MutableList<Map<Player, MatchResult>> = mutableListOf()
        for (player in players) {
            val playerMap = mapOf(player to dealer.compare(player))
            resultMap.add(playerMap)
        }
        return resultMap
    }

    fun setProfit(): List<Player> {
        val resultMapList = getResultMap()

        for (resultMap in resultMapList) {
            for ((player, matchResult) in resultMap) {
                val profit = matchResult.rate * player.bettingAmount
                player.profit = profit
                dealer.calculateProfit(profit.toInt())
            }
        }

        return players
    }
}
