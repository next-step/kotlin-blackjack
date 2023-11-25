package blackjack.model.result

import blackjack.model.playable.PlayableResult
import blackjack.model.playable.impl.Player

class PlayersResults(
    private val value: Map<Player, PlayableResult>,
) {
    fun loseCount(): Int {
        return value.values.count { it == PlayableResult.LOSE }
    }

    fun winningCount(): Int {
        return value.values.count { it == PlayableResult.WIN }
    }

    fun drawingCount(): Int {
        return value.values.count { it == PlayableResult.DRAW }
    }

    fun resultOfPlayer(player: Player): PlayableResult {
        return requireNotNull(value[player]) { "플레이어를 찾을 수 없음" }
    }

    fun playerResult(): List<Pair<String, PlayableResult>> {
        return value.keys.map { playerNameAndResult(it) }.toList()
    }

    private fun playerNameAndResult(it: Player): Pair<String, PlayableResult> {
        return it.name to requireNotNull(value[it]) { "플레이어 ${it.name} 을 찾을 수 없습니다" }
    }
}
