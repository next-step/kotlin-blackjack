package blackjack.ui

import blackjack.domain.Player

object ResultView {
    fun printFirstPhase(players: List<Player>) {
        printDrawTwoCardsEach(players)
        players.forEach { printPlayerCards(it) }
        println()
    }

    fun printPlayerCards(player: Player) {
        println("${player.name} 카드: ${getCardsToStringInfo(player)}")
    }

    private fun printDrawTwoCardsEach(players: List<Player>) {
        players.joinToString { it.name }.also {
            println("${it}에게 2장의 카드를 나누었습니다.")
        }
    }

    fun printFinalResult(players: List<Player>) {
        players.forEach { printPlayerResult(it) }
    }

    private fun printPlayerResult(player: Player) {
        val score = player.cards.calculateScore()
        println("${player.name} 카드 : ${getCardsToStringInfo(player)} - 결과 : $score")
    }

    private fun getCardsToStringInfo(player: Player): String {
        val cards = player.cards
        return cards.joinToString(", ") { "${it.rank.score} ${it.suit}" }
    }
}
