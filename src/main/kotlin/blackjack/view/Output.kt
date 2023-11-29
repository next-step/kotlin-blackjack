package blackjack.view

import blackjack.domain.BettingBoard
import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Player

fun printInitialSupply(players: List<Player>, cardNum: Int) {
    println()
    println("${players.joinToString(", ") { it.name }}에게 ${cardNum}장씩 나누었습니다.")
}

fun printUserCardInfo(name: String, cards: List<Card>) {
    println(playerCardStatus(name, cards))
}

private fun playerCardStatus(name: String, cards: List<Card>): String {
    return "${name}카드: ${cards.joinToString(", ") { cardToString(it) }}"
}

private fun cardToString(card: Card): String {
    return "${card.num}${card.suit.inKorean}"
}

fun printDealerDrawsMore() {
    println("딜러는 16점 이하라 한장의 카드를 더 받았습니다.")
}

fun printScores(players: List<Player>) {
    println()
    players.forEach {
        val result = "${playerCardStatus(it.name, it.hand.toList())} - 결과: ${it.getFinalScore()}"
        println(result)
    }
}

fun printResults(dealer: Dealer, players: List<Player>, bettingBoard: BettingBoard) {
    println()
    println("## 최종 수익")

    val playerResult = buildPlayerResults(players, bettingBoard)
    println("${dealer.name}: ${bettingBoard.getRemain()}")
    println(playerResult)
}

private fun buildPlayerResults(players: List<Player>, bettingBoard: BettingBoard): String {
    val resultString = StringBuilder()
    players.forEach {
        val profit = bettingBoard.adjustment(it.name, it.result) - bettingBoard.betOf(it.name)
        resultString.appendLine("${it.name}: $profit")
    }

    return resultString.toString()
}
