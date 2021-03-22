package blackjack.ui.model

import blackjack.domain.PlayerWinType

class PlayerWinTypes(
    private val map: MutableMap<String, PlayerWinType> = mutableMapOf()
) : MutableMap<String, PlayerWinType> by map {
    val dealerResult: String
        get() = "${findDealerWinCount()}승 ${findDealerLoseCount()}패"

    private fun findDealerWinCount(): Int = map.count { it.value == PlayerWinType.LOSE }

    private fun findDealerLoseCount() = map.count { it.value == PlayerWinType.WIN }
}
