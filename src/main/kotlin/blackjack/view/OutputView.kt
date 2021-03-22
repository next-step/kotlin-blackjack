package blackjack.view

import blackjack.domain.player.Player

fun printStartMessage(players: List<Player>) {
    println("${players.map { it.name.value }.joinToString { it }}에게 2장의 카드를 나누었습니다.")
    printPlayersCards(players)
}

fun printPlayersCards(players: List<Player>) {
    players.forEach { printPlayerCards(it) }
}

fun printPlayerCards(player: Player) {
    println("${player.name.value} 카드: ${player.cards.elements.joinToString { "${it.denomination}_${it.suit}" }}")
}
