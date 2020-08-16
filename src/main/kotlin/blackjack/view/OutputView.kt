package blackjack.view

import blackjack.domain.Player
import blackjack.domain.PlayerResult

fun notifyStartGame(dealer: Player, players: List<Player>) {
    println("딜러와 ${players.joinToString(", ") { it.info.name }}에게 2장의 카드를 나누어 주었습니다.")
    printDealerStartCard(dealer)
    players.forEach { printPlayerCards(it) }
    println()
}

private fun printDealerStartCard(dealer: Player) {
    val firstCard = dealer.cards.values[0]
    println("딜러: $firstCard")
}

fun notifyDealerHit() {
    println()
    println("딜러는 16이하라 한 장의 카드를 더 받았습니다.")
    println()
}

fun printPlayerCards(player: Player) {
    println("${player.info.name}카드: ${player.cards}")
}

fun printCardResults(dealer: Player, players: List<Player>) {
    printCardResult(dealer)
    players.forEach { printCardResult(it) }
    println()
}

private fun printCardResult(player: Player) {
    println("${player.info.name}카드: ${player.cards} - 결과: ${player.getScore()}")
}

fun printPlayerResults(dealerResult: PlayerResult, results: List<PlayerResult>) {
    println("## 최종 승패")
    printPlayerResult(dealerResult)
    results.forEach { printPlayerResult(it) }
}

fun printPlayerResult(result: PlayerResult) {
    println("${result.name}: ${result.winCount}승 ${result.loseCount}패")
}
