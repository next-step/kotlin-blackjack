package blackjack.domain

class CompareResult(
    dealer: Dealer,
    players: List<Player>
) {
    var dealerCompareResultItem: CompareResultItem = CompareResultItem()
        private set

    var playerCompareResultMap: Map<Player, CompareResultItem> = mutableMapOf<Player, CompareResultItem>().let {
        players.forEach { player ->
            it[player] = CompareResultItem()
        }
        it.toMap()
    }
        private set

    init {
        players.forEach { player ->
            val dealerResultItem = dealer.compete(player = player)

            val oldPlayerResult = playerCompareResultMap[player]

            require(oldPlayerResult != null)

            dealerCompareResultItem += dealerResultItem
            playerCompareResultMap = playerCompareResultMap
                .toMutableMap().also {
                    it[player] = oldPlayerResult + dealerResultItem.getEnemyResultItem()
                }.toMap()
        }
    }
}
