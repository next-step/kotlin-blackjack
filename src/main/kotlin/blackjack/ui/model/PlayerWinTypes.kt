package blackjack.ui.model

import blackjack.domain.WinType

class PlayerWinTypes(
    private val map: MutableMap<String, WinType> = mutableMapOf()
) : MutableMap<String, WinType> by map {
    val dealerResult: String
        get() = "${map.count { it.value == WinType.LOSE }}승 ${map.count { it.value == WinType.WIN }}패"
}
