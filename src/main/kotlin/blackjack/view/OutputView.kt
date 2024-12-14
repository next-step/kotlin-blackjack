package blackjack.view

import blackjack.BlackJackGame
import blackjack.domain.CardMark
import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.Player

object OutputView {
    private const val SPADE_STR = "스페이드"
    private const val HEART_STR = "하트"
    private const val DIAMOND_STR = "다이아몬드"
    private const val CLOVER_STR = "클로버"

    fun printPlayerCards(player: Player) {
        val outputMessage =
            buildString {
                append(player.name)
                append("카드: ")
                append(createCardsOutputMessage(player.hand.cards))
            }
        println(outputMessage)
    }

    private fun printDealerCards(dealer: Dealer) {
        val outputMessage =
            buildString {
                append("딜러: ")
                append(createCardsOutputMessage(dealer.hand.cards))
            }
        println(outputMessage)
    }

    fun printCurrentStatus(game: BlackJackGame) {
        printDealerCards(game.dealer)
        game.players.members.forEach(::printPlayerCards)
    }

    fun printGameResult(game: BlackJackGame) {
        printDealerResult(game.dealer)
        game.players.members.forEach { printPlayerResult(it) }
    }

    private fun printDealerResult(dealer: Dealer) {
        println("${printDealerCards(dealer)} - 결과: ${dealer.hand.getCardsSum()}")
    }

    private fun printPlayerResult(player: Player) {
        println("${printPlayerCards(player)} - 결과: ${player.hand.calculateCardsMaxSum()}")
    }

    private fun convertMarkToString(mark: CardMark) =
        when (mark) {
            CardMark.SPADE -> SPADE_STR
            CardMark.HEART -> HEART_STR
            CardMark.DIAMOND -> DIAMOND_STR
            CardMark.CLOVER -> CLOVER_STR
        }

    private fun createCardsOutputMessage(cards: Cards): String {
        return cards.group
            .filter { it.isFaceUp }
            .joinToString { it.number.symbol + convertMarkToString(it.mark) }
    }
}
