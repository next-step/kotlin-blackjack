package blackjack.controller

import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.PlayerCards

object ResultView {

    fun writeGameInit(initDrawCardCount: Int, players: List<Player>) {
        println("${players.joinToString(",") { it.name }} 에게 ${initDrawCardCount}장의 카드를 나누었습니다.")
        players.forEach { writePlayer(it) }
        println("")
    }

    fun writePlayer(player: Player) {
        println(formatPlayer(player))
    }

    private fun formatPlayer(player: Player): String {
        return "${player.name}카드: ${formatCards(player.cards)}"
    }

    private fun formatCards(cards: PlayerCards): String {
        return cards.joinToString(", ") { "${it.value.displayName}${it.type.displayName}" }
    }

    fun writeGameResult(result: GameResult) {
        println("")
        result.players.forEach { (player, point) ->
            println("${formatPlayer(player)} - 결과: $point")
        }
    }
}
