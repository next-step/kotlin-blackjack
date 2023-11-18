package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Player

fun printInitialSupply(players: List<Player>, cardNum: Int) {
    println("${players.joinToString(", ") { it.name }}에게 ${cardNum}장씩 나누었습니다.")
}

fun printUserCardInfo(player: Player) {
    println("${player.name}카드: ${player.getCards().joinToString(", ") { cardToString(it) }}")
}

private fun cardToString(card: Card): String {
    return "${card.num.mark}${card.suit.inKorean}"
}
