package blackjack.view

import blackjack.domain.Player

fun notifyStartGame(dealer: Player, players: List<Player>) {
    println("딜러와 ${players.joinToString(", ") { it.name }}에게 2장의 카드를 나누어 주었습니다.")
    printDealerStartCard(dealer)
    players.forEach { printPlayerCards(it) }
    println()
}

private fun printDealerStartCard(dealer: Player) {
    val firstCard = dealer.cards.values[0]
    println("딜러: $firstCard")
}

fun notifyDealerHit() {
    println("딜러는 16이하라 한 장의 카드를 더 받았습니다.")
}

fun printPlayerCards(player: Player) {
    println("${player.name}카드: ${player.cards}")
}

fun printResult(player: Player) {
    println("${player.name}카드: ${player.cards} - 결과: ${player.getScore()}")
}
