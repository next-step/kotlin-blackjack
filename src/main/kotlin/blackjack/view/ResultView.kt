package blackjack.view

import blackjack.domain.Cards
import blackjack.domain.Player
import blackjack.domain.Players

class ResultView {

    fun printStartCard(players: Players) {
        println(String.format(OUTPUT_START_CARD_BRIEFING, players.names().joinToString()))
        println()

        players.all().forEach { player ->
            val cards = formatToPrintCards(player.deck)
            println(String.format(OUTPUT_CARD_PLAYER_HAS, player, cards))
        }
        println()
    }

    fun printPlayerCard(player: Player) {
        val cards = formatToPrintCards(player.deck)
        println(String.format(OUTPUT_CARD_PLAYER_HAS, player, cards))
    }

    fun printPlayResult(players: Players) {
        println()
        players.all().forEach { player ->
            val cards = formatToPrintCards(player.deck)
            println(
                String.format(
                    OUTPUT_CARD_PLAYER_HAS, player, cards
                ) + String.format(
                    OUTPUT_PLAY_RESULT, player.getScore()
                )
            )
        }
    }

    fun printBust() {
        println(OUTPUT_BUST)
    }

    private fun formatToPrintCards(deck: Cards?): String {
        return deck?.cards?.joinToString {
            it.value.displayName + it.pattern.displayName
        } ?: ""
    }

    companion object {
        private const val OUTPUT_START_CARD_BRIEFING = "%s에게 2장의 카드를 나누었습니다."
        private const val OUTPUT_CARD_PLAYER_HAS = "%s카드: %s"
        private const val OUTPUT_PLAY_RESULT = " - 결과: %s"
        private const val OUTPUT_BUST = "받은 카드의 합이 21 초과되었습니다."
    }
}
