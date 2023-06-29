package view

import domain.card.Card
import domain.card.CardNumber
import domain.card.Suit
import domain.dto.IssuedCardResult
import domain.dto.PlayerIssuedCardsResult
import domain.game.RevenueResult
import domain.player.Player
import domain.player.PlayerGameResult

class ResultView {

    fun printInitPlayers(initGameResult: IssuedCardResult) {
        val playerNames = initGameResult.playerResults.joinToString(SEPARATOR) { it.name }
        println("${initGameResult.dealerResult.name}와 $playerNames 에게 2장을 나누었습니다.")
        println("${initGameResult.dealerResult.name}: ${printCard(initGameResult.dealerResult.cards[0])}")
        initGameResult.playerResults.forEach { printPlayerCards(it) }
        println()
    }

    fun printIssuedCardResult(issuedCardResult: IssuedCardResult) {
        println()
        printPlayerCards(issuedCardResult.dealerResult) { "- 결과 : ${issuedCardResult.dealerResult.cards.sum}" }
        issuedCardResult.playerResults.forEach { printPlayerCards(it) { "- 결과 : ${it.cards.sum}" } }
    }

    fun printRevenue(result: RevenueResult) {
        println()
        println("## 최종 수익")
        printRevenue(name = "딜러", revenueAmount = result.dealerRevenue)
        result.playersRevenues.forEach { (name, revenueAmount) ->
            printRevenue(name = name, revenueAmount = revenueAmount)
        }
    }

    private fun printRevenue(name: String, revenueAmount: Int) {
        println("$name: $revenueAmount")
    }

    private fun printPlayerResults(
        players: List<Player>,
        playerGameResult: PlayerGameResult,
    ) {
        players.forEach { player ->
            println("${player.name}: ${playerGameResult.description}")
        }
    }

    fun printPlayerCards(result: PlayerIssuedCardsResult, sumOfCardSum: () -> String = { "" }) {
        val playerCards = result.cards.joinToString(SEPARATOR) { printCard(it) }
        println("${result.name} 카드: $playerCards ${sumOfCardSum()}")
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

        private const val DEALER_ISSUED_CARD_MESSAGE = "\n딜러는 16이하라 한장의 카드를 더 받았습니다."
    }
}
