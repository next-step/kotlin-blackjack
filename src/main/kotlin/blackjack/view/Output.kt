package blackjack.view

import blackjack.domain.BlackjackUtil
import blackjack.domain.Card
import blackjack.domain.Player

fun printInitialSupply(players: List<Player>, cardNum: Int) {
    println()
    println("${players.joinToString(", ") { it.name }}에게 ${cardNum}장씩 나누었습니다.")
}

fun printUserCardInfo(player: Player) {
    println(playerCardStatus(player))
}

private fun playerCardStatus(player: Player): String {
    return "${player.name}카드: ${player.hand.toList().joinToString(", ") { cardToString(it) }}"
}

private fun cardToString(card: Card): String {
    return "${card.num}${card.suit.inKorean}"
}

fun printResult(players: List<Player>) {
    println()
    players.forEach {
        val score = BlackjackUtil.computeScore(it.hand).second
        val result = "${playerCardStatus(it)} - 결과: $score"
        println(result)
    }
}
