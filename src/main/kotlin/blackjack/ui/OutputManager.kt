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
import blackjack.participant.Result

class OutputManager {
    fun printPlayersAndDealerCards(players: List<Player>, dealer: Dealer) {
        println("딜러: ${parsingCardsToString(dealer.cards.first())}")

        players.forEach {
            println("${it.name.value}: ${parsingCardsToString(it.cards)}")
        }
    }

    fun printPlayerCards(player: Player) {
        println("${player.name.value}: ${parsingCardsToString(player.cards)}")
    }

    fun printDealerCards(dealer: Dealer) {
        println("딜러: ${parsingCardsToString(dealer.cards)}")
    }

    fun printPlayerResultGame(player: Player) {
        println("${player.name.value} 카드: ${parsingCardsToString(player.cards)} - 결과: ${player.resultScore()}")
    }

    fun printDealerResultGame(dealer: Dealer) {
        println("딜러 카드: ${parsingCardsToString(dealer.cards)} - 결과: ${dealer.resultScore()}")
    }

    fun printResult(gameResult: GameResult) {
        gameResult.resultMap.forEach {
            println("${it.key.value} : ${parsingGameResult(it.value)}")
        }
    }

    private fun parsingGameResult(result: Result): String {
        return when(result) {
            is Result.Win -> WIN
            is Result.Lose -> LOSE
            is Result.DealerResult -> "${result.win} $WIN ${result.lose} $LOSE"
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

    fun printFirstTurn2(players: List<Player>) {
        val names: String = players.joinToString(", ") { it.name.value }

        println("딜러와 ${names}에게 2장의 카드를 나누었습니다.")
    }

    fun printDealerCanDrawMessage() {
        println(DEALER_MESSAGE)
    }

    companion object {
        private const val DEALER_MESSAGE = "16이하라 한장의 카드를 더 받았습니다."
        private const val WIN = "승"
        private const val LOSE = "패"
    }
}
