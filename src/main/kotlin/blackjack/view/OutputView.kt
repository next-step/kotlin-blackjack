package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Number
import blackjack.domain.Player
import blackjack.domain.Shape

object OutputView {

    fun printPlayersCards(players: List<Player>) {
        val names = players.joinToString(separator = ", ") { it.name }
        println("${names}에게 2장의 카드를 나누었습니다")
        players.forEach { printPlayerCards(it) }
    }

    fun printPlayerCards(player: Player) {
        val hands = player.hands.joinToString(", ") { cardText(it) }
        println("${player.name} 카드 : $hands")
    }

    private fun cardText(card: Card): String {
        return numberText(card.number) + shapeText(card.shape)
    }

    private fun numberText(number: Number): String {
        return when(number) {
            Number.ACE -> "A"
            Number.JACK -> "J"
            Number.QUEEN -> "Q"
            Number.KING -> "K"
            else -> number.value.toString()
        }
    }

    private fun shapeText(shape: Shape): String {
        return when(shape) {
            Shape.DIAMOND -> "다이아몬드"
            Shape.HEART -> "하트"
            Shape.SPADE -> "스페이드"
            Shape.CLOVER -> "클로버"
        }
    }

    fun printPlayerResult(players: List<Player>) {
        for (player in players) {
            val hands = player.hands.joinToString(", ") { cardText(it) }
            println("${player.name} 카드 : $hands - 결과: ${player.sumOfCards()}")
        }
    }
}
