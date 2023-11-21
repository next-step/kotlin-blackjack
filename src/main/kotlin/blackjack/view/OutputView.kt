package blackjack.view

import blackjack.model.player.Player

object OutputView {
    fun printPlayerInitStatus(players: List<Player>) {
        val names = players.joinToString { it.name }
        println("\n${names}에게 2장의 카드를 나누었습니다.")
        players.forEach { player -> printPlayerCards(player) }
    }

    fun printPlayerCards(player: Player) {
        val card = getCard(player)
        println("${player.name} 카드 : $card")
    }

    fun printResult(players: List<Player>) {
        println()
        players.forEach { player ->
            val card = getCard(player)
            val totalScore = player.cards.calculateScore()
            println("${player.name} 카드 : $card - 결과 : $totalScore")
        }
    }

    private fun getCard(player: Player) =
        player.cards().joinToString(separator = ", ") { "${it.number}${it.suit.name}" }
}
