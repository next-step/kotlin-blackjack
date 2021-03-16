package blackjack.view

import blackjack.domain.Player

internal class OutputView {
    fun renderStartGame(players: List<Player>) {
        val names = players.map { it.name }.joinToString(SEPARATOR)
        println("\n${names}에게 2장의 나누었습니다.")

        players.forEach {
            renderPlayerCards(it)
        }
        println()
    }

    fun renderPlayerCards(player: Player) {
        val cardNames = player.cards.map { it.name }.joinToString(SEPARATOR)
        println("${player.name}카드: $cardNames")
    }

    fun renderResult(players: List<Player>) {
        println()
        players.forEach {
            val cardNames = it.cards.map { it.name }.joinToString(SEPARATOR)
            println("${it.name}카드: $cardNames - 결과: ${it.score()}")
        }
    }

    companion object {
        private const val SEPARATOR = ", "
    }
}
