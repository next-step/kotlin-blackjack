package blackjack.view

import blackjack.domain.game.BlackJack
import blackjack.domain.player.Player

class GameView(
    private val blackJack: BlackJack,
    private val players: List<Player>,
) {

    fun firstRoundState() {
        println()
        for (player in players) {
            println("${player.name}카드: ${player.cards.joinToString(", ")}")
        }
    }

    fun run() {
        println()
        players.forEach { choiceCard(it) }
    }

    private fun choiceCard(player: Player) {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readlnOrNull() ?: throw IllegalArgumentException()
        when (input) {
            "y" -> {
                blackJack.giveCard(player)
                playerCards(player)
                choiceCard(player)
            }
            "n" -> {
                playerCards(player)
                return
            }
            else -> {
                println("다시 입력해주세요. (예는 y, 아니오는 n)")
                choiceCard(player)
            }
        }
    }

    private fun playerCards(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString(", ")}")
    }
}
