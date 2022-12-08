package blackjack.ui

import blackjack.CardCalculator
import blackjack.Player

class ResultView {
    fun startTakeCardPlayers(players: List<Player>) {
        val names = players.map { it.name }
        println("${names.joinToString(", ")}에게 ${players[0].takeCards.size}장을 나누었습니다.")

        takeCardPlayers(players)
        println()
    }

    private fun takeCardPlayers(players: List<Player>) {
        players.forEach { player ->
            takeCardPlayer(player)
        }
    }

    fun takeCardPlayer(player: Player) {
        val cardNames = player.takeCards.map { it.getName() }
        println("${player.name}카드: ${cardNames.joinToString(", ")}")
    }

    fun playersResult(players: List<Player>) {
        println()
        players.forEach { player ->
            val cardNames = player.takeCards.map { it.getName() }
            val calculator = CardCalculator(player.takeCards)
            println("${player.name}카드: ${cardNames.joinToString(", ")} - 결과: ${calculator.sum()}")
        }
    }
}
