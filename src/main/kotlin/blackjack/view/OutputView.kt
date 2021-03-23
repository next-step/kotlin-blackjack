package blackjack.view

import blackjack.domain.Denomination
import blackjack.domain.Suit
import blackjack.domain.player.Player

fun printStartMessage(players: List<Player>) {
    println("${players.map { it.name.value }.joinToString { it }}에게 2장의 카드를 나누었습니다.")
    printPlayersCards(players)
}

private fun printPlayersCards(players: List<Player>) {
    players.forEach { printPlayerCards(it) }
}

fun printPlayerCards(player: Player) {
    println(
        "${player.name.value} 카드: ${player.cards.elements.joinToString { "${mapping(it.denomination)}${mapping(it.suit)}" }}"
    )
}

fun printPlayersResult(players: List<Player>) {
    println()
    players.forEach { printPlayerResult(it) }
}

private fun printPlayerResult(player: Player) {
    println(
        "${player.name.value} 카드: ${player.cards.elements.joinToString { "${mapping(it.denomination)}${mapping(it.suit)}" }} - 결과: ${player.score.value}"
    )
}

private fun mapping(denomination: Denomination) = when (denomination) {
    Denomination.ACE -> "A"
    Denomination.TWO -> "2"
    Denomination.THREE -> "3"
    Denomination.FOUR -> "4"
    Denomination.FIVE -> "5"
    Denomination.SIX -> "6"
    Denomination.SEVEN -> "7"
    Denomination.EIGHT -> "8"
    Denomination.NINE -> "9"
    Denomination.TEN -> "10"
    Denomination.JACK -> "J"
    Denomination.QUEEN -> "Q"
    Denomination.KING -> "K"
}

private fun mapping(suit: Suit) = when (suit) {
    Suit.SPADE -> "스페이드"
    Suit.HEART -> "하트"
    Suit.CLUB -> "클럽"
    Suit.DIAMOND -> "다이아몬드"
}
