package blackjack.view

import blackjack.domain.Player

object ResultView {

    private const val START_STRING = "에게 2장의 나누었습니다."

    private const val CARD_STRING = "카드:"

    private const val SCORE_STRING = "- 결과:"

    fun printCards(player: Player) {
        println("${player.name}$CARD_STRING ${player.cards.cards.joinToString { it.character.value + it.shape.value }}")
    }

    fun printStart(players: List<Player>) {
        println("\n${players.joinToString { it.name }}$START_STRING")
        players.forEach { printCards(it) }
        println()
    }

    fun printResult(players: List<Player>) {
        println()
        players.forEach { printScore(it) }
    }

    private fun printScore(player: Player) {
        println("${player.name}$CARD_STRING ${player.cards.cards.joinToString { it.character.value + it.shape.value }} $SCORE_STRING ${player.cards.score()}")
    }
}
