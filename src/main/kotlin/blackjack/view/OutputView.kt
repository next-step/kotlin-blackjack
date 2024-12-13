package blackjack.view

import blackjack.BlackJackGame
import blackjack.domain.CardMark
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
                append(player.getCards().joinToString { it.number.symbol + convertMarkToString(it.mark) })
                append(" - 결과: ")
                append(player.getCardsMaxSum())
            }

        println(outputMessage)
    }

    fun printGameResult(game: BlackJackGame) {
        game.players.members.forEach { printPlayer(it) }
    }

    fun printCurrentStatus(game: BlackJackGame) {
        game.players.members.forEach(::printPlayerCards)
    }

    private fun printPlayer(player: Player) {
        println("${printPlayerCards(player)} - 결과: ${player.getCardsMaxSum()}")
    }

    private fun convertMarkToString(mark: CardMark) =
        when (mark) {
            CardMark.SPADE -> SPADE_STR
            CardMark.HEART -> HEART_STR
            CardMark.DIAMOND -> DIAMOND_STR
            CardMark.CLOVER -> CLOVER_STR
        }
}
