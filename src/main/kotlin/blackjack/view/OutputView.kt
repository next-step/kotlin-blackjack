package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

object OutputView {

    private const val SEPARATOR = ", "

    fun printPlayers(players: Players) {
        val output = buildString {
            val names = players.joinToString(SEPARATOR) { it.name }
            append(System.lineSeparator())
            append("${names}에게 2장을 나누었습니다.")
        }
        println(output)
    }

    fun printPlayersDrawnCards(players: Players) {
        val output = buildString {
            players.forEach { player ->
                append("${player.name}카드: ")
                append(player.cards.joinToString { card -> "${card.numberType.displayName}${card.suitType.displayName}" })
                append(System.lineSeparator())
            }
        }
        println(output)
    }

    fun printPlayerDrawnCard(player: Player) {
        val output = buildString {
            append("${player.name}카드: ")
            append(player.cards.joinToString { card -> "${card.numberType.displayName}${card.suitType.displayName}" })
        }
        println(output)
    }
}
