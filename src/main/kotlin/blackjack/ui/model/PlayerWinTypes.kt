package blackjack.ui.model

import blackjack.domain.WinType

class PlayerWinTypes(
    private val map: MutableMap<String, WinType> = mutableMapOf()
) : MutableMap<String, WinType> by map {
    val dealerResult: String
        get() = "${findDealerWinCount()}승 ${findDealerLoseCount()}패"

    private fun findDealerWinCount(): Int = map.count { it.value == WinType.LOSE }

    private fun findDealerLoseCount() = map.count { it.value == WinType.WIN }
}
