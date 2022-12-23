package blackjack.view

import blackjack.domain.Player

class OutputView {

    fun printSetUp(players: List<Player>) {
        val cardSize = players[0].cards.size
        val playerNames = players.map { it.name }.joinToString(", ")
        println()
        println("${playerNames}에게 ${cardSize}장의 카드를 나누었습니다.")

        players.forEach {
            printEachPlayer(it)
        }
        println()
    }

    fun printEachPlayer(player: Player) {
        val cards = playerCards(player)
        println("${player.name}카드: $cards")
    }

    private fun playerCards(player: Player) =
        player.cards.joinToString(", ") { "${it.cardNumber.displayName}${it.cardSuit.displayName}" }

    fun printResult(players: List<Player>) {
        println()
        players.forEach {
            val cards = playerCards(it)
            val score = playerCardsScore(it)
            println("${it.name}카드: $cards - 결과: $score")
        }
    }

    private fun playerCardsScore(player: Player): String {
        return player.totalScore().toString()
    }
}
