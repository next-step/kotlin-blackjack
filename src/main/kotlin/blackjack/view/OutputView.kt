package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Player

object OutputView {
    fun firstCard(players: List<Player>, dealer: Dealer) {
        val allPlayers = allPlayer(players, dealer)
        val result = buildString {
            appendLine("\n${allPlayers.joinToString(", ") { it.name }} 에게 2장의 카드를 나누어 주었습니다.")
            allPlayers.forEach { player ->
                appendLine(playerCardToString(player))
            }
        }
        print(result)
    }

    fun cardOfPlayer(player: Player) {
        println(playerCardToString(player))
    }

    fun result(players: List<Player>, dealer: Dealer) {
        val allPlayers = allPlayer(players, dealer)
        val result = buildString {
            appendLine("==========================")
            allPlayers.forEach { player ->
                append(playerCardToString(player))
                appendLine(" - 결과: ${player.sumOfPoints()}")
            }
        }
        println(result)
    }

    private fun playerCardToString(player: Player) = buildString {
        append(
            "${player.name}카드: ${
            player.hands.cards.joinToString(", ") { it.number.displayStr + it.symbol.displayStr }
            }"
        )
    }

    private fun allPlayer(players: List<Player>, dealer: Dealer): List<Player> {
        val allPlayers = players.toMutableList()
        allPlayers.add(0, dealer)
        return allPlayers.toList()
    }
}
