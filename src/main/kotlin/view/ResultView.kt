package view

import domain.card.CardNumber
import domain.card.Suit
import domain.player.Player
import domain.player.Players

class ResultView {

    fun printInitPlayers(players: Players) {
        val playerNames = players.map { it.name }.joinToString { "," }
        println("$playerNames 에게 2장을 나누었습니다.")
        players.forEach { printPlayerCards(it) }
        println()
    }

    fun printGameResult(players: Players) {
        players.forEach { printPlayerCards(it) { "- 결과 : ${it.cards.sum}" } }
    }

    fun printPlayerCards(player: Player, sumOfCardSum: () -> String = { "" }) {
        val playerCards = player.cards.map {
            "${CARD_NUMBER_SHAPE_MAP[it.number]}${CARD_SUIT_SHAPE_MAP[it.suit]}"
        }.joinToString { DELIMITERS }
        println("${player.name}: $playerCards ${sumOfCardSum()}")
    }

    companion object {
        private val CARD_NUMBER_SHAPE_MAP = mapOf(
            CardNumber.ACE to "A",
            CardNumber.TWO to "2",
            CardNumber.THREE to "3",
            CardNumber.FOUR to "4",
            CardNumber.FIVE to "5",
            CardNumber.SIX to "6",
            CardNumber.SEVEN to "7",
            CardNumber.EIGHT to "8",
            CardNumber.NINE to "9",
            CardNumber.TEN to "10",
            CardNumber.JACK to "J",
            CardNumber.KING to "K",
            CardNumber.QUEEN to "Q",
        )

        private val CARD_SUIT_SHAPE_MAP = mapOf(
            Suit.HEART to "하트",
            Suit.SPADE to "스페이드",
            Suit.CLUB to "클로버",
            Suit.DIAMOND to "다이아몬드",
        )

        private const val DELIMITERS = ","
    }
}
