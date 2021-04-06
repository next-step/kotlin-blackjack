package blackjack.domain

class PlayerWinTypes(
    private val map: Map<String, PlayerWinType>
) : Map<String, PlayerWinType> by map {
    val dealerResult: String
        get() = "${findDealerWinCount()}승 ${findDealerLoseCount()}패"

    private fun findDealerWinCount(): Int = map.count { PlayerWinType.isLose(it.value) }
    private fun findDealerLoseCount(): Int = map.count { PlayerWinType.isWin(it.value) }

    companion object {
        fun of(players: Players, dealerPoint: PlayerPoint): PlayerWinTypes {
            val winTypeMap = players.associate {
                val playerPoint = PlayerPoint(it.cardPointSum(), it.isBlackjack)
                val playerWinType = PlayerWinType.findPlayerWinType(playerPoint, dealerPoint)
                Pair(it.name, playerWinType)
            }
            return PlayerWinTypes(winTypeMap)
        }
    }
}
