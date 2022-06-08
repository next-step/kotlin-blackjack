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
        if (input == "y") {
            blackJack.giveCard(player)
            playerCards(player)
            choiceCard(player)
        } else if (input == "n") {
            playerCards(player)
            return
        }
    }

    private fun playerCards(player: Player) {
        println("${player.name}카드: ${player.cards.joinToString(", ")}")
    }
}
