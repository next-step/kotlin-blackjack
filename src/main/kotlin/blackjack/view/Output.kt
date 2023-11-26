package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Result

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

fun printResults(dealer: Dealer, players: List<Player>) {
    println("## 최종 승패")
    println("${dealer.name}: ${buildDealerResult(players)}")

    players.forEach {
        println("${it.name}: ${it.result.inKorean}")
    }
}

private fun buildDealerResult(players: List<Player>): String {
    val resultString = StringBuilder()
    Result.values().forEach { result ->
        resultString.append("${players.count { it.result == result }}${result.inKorean} ")
    }

    return resultString.toString()
}
