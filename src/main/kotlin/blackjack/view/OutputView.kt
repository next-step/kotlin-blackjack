package blackjack.view

import blackjack.domain.Player

fun notifyStartGame(players: List<Player>) {
    println("${players.joinToString(", ") { it.name }}에게 2장의 카드를 나누어 주었습니다.")
    players.forEach { printPlayerCards(it) }
    println()
}

fun printPlayerCards(player: Player) {
    println("${player.name}카드: ${player.cards}")
}

fun printResult(player: Player) {
    println("${player.name}카드: ${player.cards} - 결과: ${player.getScore()}")
}
