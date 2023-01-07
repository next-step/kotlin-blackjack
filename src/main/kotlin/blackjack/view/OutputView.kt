package blackjack.view

import blackjack.domain.Participant

object OutputView {
    fun printInitialCards(players: List<Participant>) {
        println("${players.joinToString(", ") { it.name }}에게 2장을 나누어 주었습니다.")
        players.forEach { player ->
            println("${player.name}: ${cards(player)}")
        }
    }

    fun printPlayerCards(player: Participant) {
        println("${player.name}카드: ${cards(player)}")
    }

    fun printGameResult(players: List<Participant>) {
        println(players.joinToString("\n") { "${it.name}카드: ${cards(it)} - 결과: ${it.getScore()}" })
    }

    private fun cards(player: Participant): String {
        return player.cards.joinToString(", ") { it.toString() }
    }
}
