package blackjack.views

import blackjack.domain.BlackJack
import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardSymbol
import blackjack.domain.Player

class OutputView {

    fun printInitialDrawResult(blackJack: BlackJack) {
        val players = blackJack.players

        println(INITIAL_DRAW_RESULT_MSG.format(players.joinToString { it.name }))
        printPlayerStatus(blackJack.dealer)
        players.forEach { printPlayerStatus(it) }
        println()
    }

    fun printPlayerStatus(player: Player) {
        println(buildPlayerStatusOutput(player))
    }

    fun printDealerDraw() {
        println(DEALER_DRAW_MSG)
    }

    fun printResult(blackJack: BlackJack) {
        println()
        printPlayerResult(blackJack.dealer)
        blackJack.players.forEach { printPlayerResult(it) }
        printWinLose(blackJack)
    }

    private fun printPlayerResult(player: Player) {
        val playerStatus = buildPlayerStatusOutput(player)
        println(playerStatus + GAME_RESULT_MSG.format(player.getTotalScore()))
    }

    private fun buildPlayerStatusOutput(player: Player): String {
        return PLAYER_STATUS_MSG.format(player.name, player.cards.joinToString { buildCardOutput(it) })
    }

    private fun buildCardOutput(card: Card): String {
        return CARD_OUTPUT_TEMPLATE.format(
            convertCardNumberToString(card.number),
            convertSymbolToString(card.symbol)
        )
    }

    private fun convertCardNumberToString(cardNumber: CardNumber): String {
        return when (cardNumber) {
            CardNumber.ACE -> ACE_TEXT
            CardNumber.KING -> KING_TEXT
            CardNumber.QUEEN -> QUEEN_TEXT
            CardNumber.JACK -> JACK_TEXT
            else -> cardNumber.value.toString()
        }
    }

    private fun convertSymbolToString(cardSymbol: CardSymbol): String {
        return when (cardSymbol) {
            CardSymbol.HEART -> HEART_TEXT
            CardSymbol.CLOVER -> CLOVER_TEXT
            CardSymbol.DIAMOND -> DIAMOND
            CardSymbol.SPADE -> SPADE
        }
    }

    private fun printWinLose(blackJack: BlackJack) {
        println()
        println(WIN_LOSE_RESULT_TITLE)
        blackJack.apply {
            val winners = getWinners()
            val dealerWinLoseCount = getDealerWinLoseCount()
            println(DEALER_WIN_LOSE_MSG.format(dealerWinLoseCount.winCount, dealerWinLoseCount.loseCount))

            players.forEach {
                if (it in winners) {
                    println(PLAYER_WIN_MSG.format(it.name))
                } else {
                    println(PLAYER_LOSE_MSG.format(it.name))
                }
            }
        }
    }

    companion object {
        private const val INITIAL_DRAW_RESULT_MSG = "딜러와 %s 에게 각각 2장의 카드를 전달하였습니다."
        private const val DEALER_DRAW_MSG = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val PLAYER_STATUS_MSG = "%s 카드: %s"
        private const val GAME_RESULT_MSG = " - 결과: %s"
        private const val ACE_TEXT = "A"
        private const val KING_TEXT = "K"
        private const val QUEEN_TEXT = "Q"
        private const val JACK_TEXT = "J"
        private const val HEART_TEXT = "하트"
        private const val CLOVER_TEXT = "클로버"
        private const val DIAMOND = "다이아몬드"
        private const val SPADE = "스페이드"
        private const val CARD_OUTPUT_TEMPLATE = "[%s-%s]"
        private const val WIN_LOSE_RESULT_TITLE = "## 최종 승패 ##"
        private const val DEALER_WIN_LOSE_MSG = "딜러: %s승 %s패"
        private const val PLAYER_WIN_MSG = "%s: 승"
        private const val PLAYER_LOSE_MSG = "%s: 패"
    }
}
