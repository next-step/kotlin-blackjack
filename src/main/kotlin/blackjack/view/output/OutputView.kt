package blackjack.view.output

import blackjack.domain.card.Card
import blackjack.view.dto.FinalPlayerStateDto
import blackjack.view.dto.PlayerDto

object OutputView {
    private const val INITIAL_DISTRIBUTION_MSG = "%s에게 2장씩 나누었습니다."
    private const val PLAYER_STATE_MSG = "%s카드: %s"
    private const val RESULT_MSG = "%s카드: %s - 결과: %d"

    fun initialDistributionResult(
        players: List<PlayerDto>,
    ) {
        println()
        println(INITIAL_DISTRIBUTION_MSG.format(extractPlayerNames(players)))
        players.forEach {
            println(PLAYER_STATE_MSG.format(it.name, extractCardsState(it.cards)))
        }
        println()
    }

    fun playerCurrentState(
        player: PlayerDto,
    ) {
        println(PLAYER_STATE_MSG.format(player.name, extractCardsState(player.cards)))
    }

    fun playerFinalStates(
        players: List<FinalPlayerStateDto>,
    ) {
        println()
        players.forEach {
            println(RESULT_MSG.format(it.name, extractCardsState(it.cards), it.score))
        }
    }

    private fun extractPlayerNames(players: List<PlayerDto>): String =
        players.joinToString(", ") { it.name }

    private fun extractCardsState(cards: List<Card>): String =
        cards.joinToString(", ") { CardView.from(it) }
}
