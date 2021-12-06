package blackjack.view

import blackjack.domain.Denomination
import blackjack.domain.Player
import blackjack.domain.Suit

fun printCardsServedFirstToPlayers(players: List<Player>) =
    println("${players}에게 2장의 나누었습니다.").also {
        players.forEach { printPlayerCards(it) }
        println()
    }

fun printPlayerCards(player: Player) =
    println("${player}카드: ${convertCardsToShape(player)}")

fun printResult(players: List<Player>) = with(players) {
    this.forEach {
        println(
            "${it}카드: ${convertCardsToShape(it)} - 결과: ${it.score}"
        )
    }
    println()
}

private fun convertCardsToShape(player: Player) =
    player.cards.joinToString { mapping(it.denomination) + mapping(it.suit) }

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
    Denomination.ACE_ELEVEN -> "A"
}

private fun mapping(suit: Suit) = when (suit) {
    Suit.SPADES -> "스페이드"
    Suit.HEARTS -> "하트"
    Suit.CLUBS -> "클럽"
    Suit.DIAMONDS -> "다이아몬드"
}
