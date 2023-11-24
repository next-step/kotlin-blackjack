package blackjack.view

import blackjack.domain.BlackjackUtil
import blackjack.domain.Card
import blackjack.domain.Player

fun printInitialSupply(players: List<Player>, cardNum: Int) {
    println()
    println("${players.joinToString(", ") { it.name }}에게 ${cardNum}장씩 나누었습니다.")
}

fun printUserCardInfo(player: Player, forInitial: Boolean = false) {
    val cards = if (forInitial) {
        player.initialCards()
    } else {
        player.hand.toList()
    }

    println(playerCardStatus(player.name, cards))
}

private fun playerCardStatus(name: String, cards: List<Card>): String {
    return "${name}카드: ${cards.joinToString(", ") { cardToString(it) }}"
}

private fun cardToString(card: Card): String {
    return "${card.num}${card.suit.inKorean}"
}

fun printResult(players: List<Player>) {
    println()
    players.forEach {
        val score = BlackjackUtil.computeScore(it.hand).second
        val result = "${playerCardStatus(it.name, it.hand.toList())} - 결과: $score"
        println(result)
    }
}

fun printDealerDrawsMore() {
    println("딜러는 16점 이하라 한장의 카드를 더 받았습니다.")
}
