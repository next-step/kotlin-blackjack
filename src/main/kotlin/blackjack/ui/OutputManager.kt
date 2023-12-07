package blackjack.ui

import blackjack.GameResult
import blackjack.card.AceCard
import blackjack.card.BlackJackCard
import blackjack.card.CardPattern
import blackjack.card.CardPicture
import blackjack.card.NormalCard
import blackjack.card.PictureCard
import blackjack.participant.Dealer
import blackjack.participant.Player

class OutputManager {
    fun printPlayersAndDealerCards(players: List<Player>, dealer: Dealer) {
        println("딜러: ${parsingCardsToString(dealer.cards.first())}")

        players.forEach {
            println("${it.name.value}: ${parsingCardsToString(it.cards)}")
        }

        printEnter()
    }

    fun printPlayerCards(player: Player) {
        println("${player.name.value}: ${parsingCardsToString(player.cards)}")
    }

    fun printPlayerResultGame(player: Player) {
        println("${player.name.value} 카드: ${parsingCardsToString(player.cards)} - 결과: ${player.resultScore()}")
    }

    fun printDealerResultGame(dealer: Dealer) {
        printEnter()
        println("딜러 카드: ${parsingCardsToString(dealer.cards)} - 결과: ${dealer.resultScore()}")
    }

    fun printResult(gameResult: GameResult) {
        printEnter()
        println(RESULT_MESSAGE)
        gameResult.resultMap.forEach {
            println("${it.key.value} : ${it.value.amount}")
        }
    }

    private fun parsingCardsToString(cards: List<BlackJackCard>): String {
        return cards.joinToString(", ") { parsingCardToString(it) }
    }

    private fun parsingCardsToString(cards: BlackJackCard): String {
        return parsingCardToString(cards)
    }

    private fun parsingCardToString(card: BlackJackCard): String {
        return when (card) {
            is NormalCard -> "${card.number}${parsingCardPatternToString(card.pattern)}"
            is PictureCard -> "${parsingCardPictureToString(card.picture)}${parsingCardPatternToString(card.pattern)}"
            is AceCard -> "ace${parsingCardPatternToString(card.pattern)}"
        }
    }

    private fun parsingCardPatternToString(cardPattern: CardPattern): String {
        return when (cardPattern) {
            CardPattern.DIAMOND -> "다이아"
            CardPattern.CLOVER -> "클로버"
            CardPattern.SPADE -> "스페이드"
            CardPattern.HEART -> "하트"
        }
    }

    private fun parsingCardPictureToString(cardPicture: CardPicture): String {
        return when (cardPicture) {
            CardPicture.KING -> "K"
            CardPicture.JACK -> "J"
            CardPicture.QUEEN -> "Q"
        }
    }

    fun printFirstTurn(players: List<Player>) {
        printEnter()
        val names: String = players.joinToString(", ") { it.name.value }

        println("딜러와 ${names}에게 2장의 카드를 나누었습니다.")
    }

    fun printDealerCanDrawMessage() {
        printEnter()
        println(DEALER_MESSAGE)
    }

    private fun printEnter() {
        println()
    }

    companion object {
        private const val DEALER_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val RESULT_MESSAGE = "## 최종승패"
    }
}
