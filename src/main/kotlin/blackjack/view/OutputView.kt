package blackjack.view

import blackjack.domain.Player

object OutputView {

    fun printInitCard(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }}에게 2장의 나누었습니다.")
    }

    fun printPlayerCard(players: List<Player>) {
        players.forEach {
            println("${it.name}카드: ${it.playerCard.cards.joinToString(", ")}")
        }
    }

    fun printPlayerResult(playerName: String, cards: List<String>, score: Int) {
        println("${playerName}카드: ${cards.joinToString(", ")} - 결과: $score")
    }
}
