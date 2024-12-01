package blackjack.ui

import blackjack.domain.Card
import blackjack.domain.Game
import blackjack.domain.Hand
import blackjack.domain.Player

object ResultView {
    private const val COMMA_SEPARATOR = ", "
    private const val BUSTED = "🪦"

    fun displayState(
        game: Game,
        isInitial: Boolean = true,
    ) {
        val roster = game.players.roster
        val names = roster.map { it.name }
        val message =
            buildString {
                appendLine()
                if (isInitial) {
                    appendLine("${names.joinToString(COMMA_SEPARATOR)}에게 2장의 나누었습니다.")
                }
                roster.forEach { appendLine(formatPlayer(it, isInitial)) }
            }
        print(message)
    }

    private fun formatPlayer(
        player: Player,
        isInitial: Boolean,
    ): String {
        val result = "${player.name}카드: ${formatHand(player.hand)}"
        if (isInitial) {
            return result
        }
        return result + " - 결과: ${if (player.isDone) BUSTED else player.value}"
    }

    private fun formatHand(hand: Hand): String =
        hand.cards
            .map { formatCard(it) }
            .joinToString(COMMA_SEPARATOR)

    private fun formatCard(card: Card): String = "${card.rank.display}${card.suit.display}"
}
