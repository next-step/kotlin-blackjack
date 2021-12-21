package blackjack.domain

@JvmInline
value class PlayerBettings private constructor(val bettingsMap: Map<Name, Money>) {

    fun calculateDealerProfit(dealer: Dealer, players: Players): Int {
        return players.players
            .filter { bettingsMap.containsKey(it.name) }
            .map { dealer.profit(it, bettingsMap[it.name]!!) }
            .reduce { acc: Int, profit: Int -> acc + profit }
    }

    fun calculatePlayersProfit(dealer: Dealer, players: Players): Map<Name, Int> {
        return players.players
            .filter { bettingsMap.containsKey(it.name) }
            .associate { it.name to it.profit(dealer, bettingsMap[it.name]!!) }
    }

    companion object {

        fun from(bettingMoneys: Map<Name, Money>): PlayerBettings {
            return PlayerBettings(bettingMoneys.toMap())
        }
    }
}
