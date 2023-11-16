package blackjack.view.output

import blackjack.domain.card.Card
import blackjack.view.model.PlayerView

object OutputView {
    private const val INITIAL_DISTRIBUTION_MSG = "%s에게 2장씩 나누었습니다."
    private const val PLAYER_STATE_MSG = "%s카드: %s"

    fun initialDistributionResult(
        players: List<PlayerView>,
    ) {
        println(INITIAL_DISTRIBUTION_MSG.format(extractPlayerNames(players)))
        players.forEach {
            println(PLAYER_STATE_MSG.format(it.name, extractCardsState(it.cards)))
        }
    }

    private fun extractPlayerNames(players: List<PlayerView>): String =
        players.joinToString(", ") { it.name }

    private fun extractCardsState(cards: List<Card>): String =
        cards.joinToString(", ") { CardView.of(it) }
}
