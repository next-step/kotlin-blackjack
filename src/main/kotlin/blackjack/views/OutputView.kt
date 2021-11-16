package blackjack.views

import blackjack.domain.Card
import blackjack.domain.Player

class OutputView {

    fun printInitialDrawResult(players: List<Player>) {
        println(INITIAL_DRAW_RESULT_MSG.format(players.joinToString { it.name }))
        players.forEach { printPlayerStatus(it) }
        println()
    }

    fun printPlayerStatus(player: Player) {
        println(buildPlayerStatusOutput(player))
    }

    fun printResult(players: List<Player>) {
        players.forEach {
            val playerStatus = buildPlayerStatusOutput(it)
            println(playerStatus + GAME_RESULT_MSG.format(it.getTotalScore()))
        }
    }

    private fun buildPlayerStatusOutput(player: Player): String {
        return PLAYER_STATUS_MSG.format(player.name, player.cards.joinToString { buildCardOutput(it) })
    }

    private fun buildCardOutput(card: Card): String {
        val cardNumberText = when (card.number.value) {
            ACE_NUMBER -> ACE_TEXT
            JACK_NUMBER -> JACK_TEXT
            QUEEN_NUMBER -> QUEEN_TEXT
            KING_NUMBER -> KING_TEXT
            else -> card.number.value.toString()
        }

        return CARD_OUTPUT_TEMPLATE.format(cardNumberText, card.symbol.displayName)
    }

    companion object {
        private const val INITIAL_DRAW_RESULT_MSG = "%s 에게 각각 2장의 카드를 전달하였습니다."
        private const val PLAYER_STATUS_MSG = "%s 카드: %s"
        private const val GAME_RESULT_MSG = " - 결과: %s"
        private const val ACE_NUMBER = 1
        private const val KING_NUMBER = 13
        private const val QUEEN_NUMBER = 12
        private const val JACK_NUMBER = 11
        private const val ACE_TEXT = "A"
        private const val KING_TEXT = "K"
        private const val QUEEN_TEXT = "Q"
        private const val JACK_TEXT = "J"
        private const val CARD_OUTPUT_TEMPLATE = "[%s-%s]"
    }
}
