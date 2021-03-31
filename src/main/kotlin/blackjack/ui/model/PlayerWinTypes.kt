package blackjack.ui.model

import blackjack.domain.PlayerWinType
import blackjack.domain.Players

class PlayerWinTypes(
    private val map: Map<String, PlayerWinType>
) : Map<String, PlayerWinType> by map {
    val dealerResult: String
        get() = "${findDealerWinCount()}승 ${findDealerLoseCount()}패"

    private fun findDealerWinCount(): Int = map.count { PlayerWinType.isLose(it.value) }
    private fun findDealerLoseCount(): Int = map.count { PlayerWinType.isWin(it.value) }

    companion object {
        fun of(players: Players, dealerPoint: Int): PlayerWinTypes {
            val winTypeMap = players.associate {
                val playerWinType = PlayerWinType.findPlayerWinType(it.cardPointSum(), dealerPoint)
                Pair(it.name, playerWinType)
            }
            return PlayerWinTypes(winTypeMap)
        }
    }
}
