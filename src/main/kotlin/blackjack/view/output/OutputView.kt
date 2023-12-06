package blackjack.view.output

import blackjack.domain.card.Card
import blackjack.view.dto.DealerCardsResultDto
import blackjack.view.dto.DealerHitDto
import blackjack.view.dto.DealerProfitDto
import blackjack.view.dto.PlayerCardsResultDto
import blackjack.view.dto.PlayerDto
import blackjack.view.dto.PlayerProfitDto

object OutputView {
    private const val INITIAL_DISTRIBUTION_MSG = "딜러와 %s에게 2장씩 나누었습니다."
    private const val DEALER_STATE_MSG = "딜러: %s"
    private const val PLAYER_STATE_MSG = "%s카드: %s"
    private const val DEALER_HIT_MSG = "딜러는 16이하라 한 장의 카드를 더 받았습니다."
    private const val DEALER_STAND_MSG = "딜러는 17이상이라 카드를 받지 않았습니다."
    private const val DEALER_FINAL_CARDS_MSG = "딜러 카드: %s - 결과: %d"
    private const val PLAYER_FINAL_CARDS_MSG = "%s카드: %s - 결과: %d"
    private const val PROFIT_MSG = "## 최종 수익"
    private const val DEALER_PROFIT_MSG = "딜러: %s"
    private const val PLAYER_PROFIT_MSG = "%s: %s"

    fun drawInitialDistributionResult(
        players: List<PlayerDto>,
        dealer: PlayerDto,
    ) {
        println()
        println(INITIAL_DISTRIBUTION_MSG.format(extractPlayerNames(players)))
        println(DEALER_STATE_MSG.format(extractCardsState(dealer.cards)))
        players.forEach {
            println(PLAYER_STATE_MSG.format(it.name, extractCardsState(it.cards)))
        }
        println()
    }

    fun drawPlayerCurrentState(
        player: PlayerDto,
    ) {
        println(PLAYER_STATE_MSG.format(player.name, extractCardsState(player.cards)))
    }

    fun drawDealerHitStatus(
        dealerHit: DealerHitDto,
    ) {
        println()
        when (dealerHit.isHit) {
            true -> println(DEALER_HIT_MSG)
            false -> println(DEALER_STAND_MSG)
        }
    }

    fun drawCardsResults(
        dealer: DealerCardsResultDto,
        players: List<PlayerCardsResultDto>,
    ) {
        println()
        println(DEALER_FINAL_CARDS_MSG.format(extractCardsState(dealer.cards), dealer.score))
        players.forEach {
            println(PLAYER_FINAL_CARDS_MSG.format(it.name, extractCardsState(it.cards), it.score))
        }
    }

    fun drawProfitResults(
        dealer: DealerProfitDto,
        players: List<PlayerProfitDto>,
    ) {
        println()
        println(PROFIT_MSG)
        println(DEALER_PROFIT_MSG.format(dealer.profit))
        players.forEach {
            println(PLAYER_PROFIT_MSG.format(it.name, it.profit))
        }
    }

    private fun extractPlayerNames(players: List<PlayerDto>): String =
        players.joinToString(", ") { it.name }

    private fun extractCardsState(cards: List<Card>): String =
        cards.joinToString(", ") { CardView.from(it) }
}
