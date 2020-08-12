package blackjack.view

import blackjack.domain.Player

object ResultView {

    fun showResultOfSetUp(players: List<Player>) {
        println("\n${players.joinToString()}에게 2장의 카드를 나누었습니다")
        players.forEach { println("${it}카드: ${it.displayCards()}") }
        println()
    }

    fun showStateOfCards(player: Player) {
        println("${player}카드: ${player.displayCards()}")
    }

    fun showFinalResult(players: List<Player>) {
        println()
        players.forEach { println("${it}카드: ${it.displayCards()} - 결과: ${it.sumOfScores()}") }
    }
}
