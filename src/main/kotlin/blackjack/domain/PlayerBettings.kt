package blackjack.domain

@JvmInline
value class PlayerBettings(val bettingsMap: Map<Name, Money>) {

    private fun getMoney(name: Name) = bettingsMap[name]
        ?: throw NullPointerException(BETTINGS_MAP_NULL_POINTER_EXCEPTION_MESSAGE)

    fun calculateDealerProfit(dealer: Dealer, players: Players): Int {
        return players.players
            .map { dealer.profit(it, getMoney(it.name)) }
            .fold(0) { acc: Int, profit: Int -> acc + profit }
    }

    fun calculatePlayersProfit(dealer: Dealer, players: Players): Map<Name, Int> {
        return players.players
            .associate { it.name to it.profit(dealer, getMoney(it.name)) }
    }

    companion object {
        const val BETTINGS_MAP_NULL_POINTER_EXCEPTION_MESSAGE = "입력한 Player의 배팅정보가 없습니다."

        fun from(bettingMoneys: Map<Name, Money>): PlayerBettings {
            return PlayerBettings(bettingMoneys)
        }
    }
}
