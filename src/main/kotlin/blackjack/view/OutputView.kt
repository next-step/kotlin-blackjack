package blackjack.view

import blackjack.domain.Player

class OutputView {
    fun firstCard(players: List<Player>) {
        val result = buildString {
            appendLine("${players.joinToString(", ") { it.name }} 에게 2장의 카드를 나누어 주었습니다.")
            players.forEach { player ->
                appendLine(playerCardToString(player))
            }
        }
        print(result)
    }

    private fun playerCardToString(player: Player) = buildString {
        append("${player.name}카드: ${player.cards.joinToString(", ")}")
    }
}
