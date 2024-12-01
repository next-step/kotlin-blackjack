package blackjack.ui

import blackjack.domain.Card
import blackjack.domain.Game
import blackjack.domain.Hand
import blackjack.domain.Player

object ResultView {
    private const val COMMA_SEPARATOR = ", "

    fun displayInitialState(game: Game) {
        val roster = game.players.roster
        val names = roster.map { it.name }
        val message =
            buildString {
                appendLine()
                appendLine("${names.joinToString(COMMA_SEPARATOR)}에게 2장의 나누었습니다.")
                roster.forEach { appendLine(formatPlayer(it)) }
            }
        println(message)
    }

    private fun formatPlayer(player: Player): String = "${player.name}카드: ${formatHand(player.hand)}"

    private fun formatHand(hand: Hand): String =
        hand.cards
            .map { formatCard(it) }
            .joinToString(COMMA_SEPARATOR)

    private fun formatCard(card: Card): String = "${card.rank.display}${card.suit.display}"
}
