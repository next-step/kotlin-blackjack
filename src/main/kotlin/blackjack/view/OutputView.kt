package blackjack.view

import blackjack.domain.Player

fun printCardsServedFirstToPlayers(players: List<Player>) =
    println("${players}에게 2장의 나누었습니다.").also {
        players.forEach { printPlayerCards(it) }
        println()
    }

fun printPlayerCards(player: Player) = println("${player}카드: ${player.cards}")

fun printResult(players: List<Player>) = with(players) {
    this.forEach { println("${it}카드: ${it.cards} - 결과: ${it.score}") }
    println()
}
