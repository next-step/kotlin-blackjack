package view

import domain.card.Card
import domain.card.CardNumber
import domain.card.Suit
import domain.player.Dealer
import domain.player.Player
import domain.player.Players

class ResultView {

    fun printInitPlayers(players: Players, dealer: Dealer) {
        val playerNames = players.map { it.name }.joinToString(SEPARATOR)
        println("${dealer.name}와 $playerNames 에게 2장을 나누었습니다.")
        println("${dealer.name}: ${printCard(dealer.cards[0])}")
        players.forEach { printPlayerCards(it) }
        println()
    }

    fun printGameResult(players: Players, dealer: Dealer) {
        println()
        printPlayerCards(dealer) { "- 결과 : ${dealer.cards.sum}" }
        players.forEach { printPlayerCards(it) { "- 결과 : ${it.cards.sum}" } }
    }

    fun printPlayerCards(player: Player, sumOfCardSum: () -> String = { "" }) {
        val playerCards = player.cards.joinToString(SEPARATOR) { printCard(it) }
        println("${player.name} 카드: $playerCards ${sumOfCardSum()}")
    }

    private fun printCard(card: Card) = "${CARD_NUMBER_SHAPE_MAP[card.number]}${CARD_SUIT_SHAPE_MAP[card.suit]}"

    fun printDealerIssuedCardMessage() {
        println(DEALER_ISSUED_CARD_MESSAGE)
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

        private const val SEPARATOR = ", "

        private const val CANNOT_PROCEED_GAME_MESSAGE = "은(는) 더 이상 게임을 진행할 수 없습니다."

        private const val DEALER_ISSUED_CARD_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    }
}
