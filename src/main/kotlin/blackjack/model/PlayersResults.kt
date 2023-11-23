package blackjack.model

class PlayersResults(
    private val value: Map<Player, PlayerResult>,
) {
    fun loseCount(): Int {
        return value.values.count { it == PlayerResult.LOSE }
    }

    fun winningCount(): Int {
        return value.values.count { it == PlayerResult.WIN }
    }

    fun drawingCount(): Int {
        return value.values.count { it == PlayerResult.DRAW }
    }

    fun resultOfPlayer(player: Player): PlayerResult {
        return requireNotNull(value[player]) { "플레이어를 찾을 수 없음" }
    }
}
