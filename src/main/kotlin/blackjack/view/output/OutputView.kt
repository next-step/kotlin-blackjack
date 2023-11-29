package blackjack.view.output

import blackjack.domain.card.Card
import blackjack.domain.result.game.VictoryStatues
import blackjack.domain.result.game.VictoryStatus
import blackjack.view.dto.DealerHitDto
import blackjack.view.dto.FinalDealerStateDto
import blackjack.view.dto.FinalPlayerStateDto
import blackjack.view.dto.PlayerDto

object OutputView {
    private const val INITIAL_DISTRIBUTION_MSG = "딜러와 %s에게 2장씩 나누었습니다."
    private const val DEALER_STATE_MSG = "딜러: %s"
    private const val PLAYER_STATE_MSG = "%s카드: %s"
    private const val DEALER_HIT_MSG = "딜러는 16이하라 한 장의 카드를 더 받았습니다."
    private const val DEALER_STAND_MSG = "딜러는 17이상이라 카드를 받지 않았습니다."
    private const val DEALER_FINAL_CARDS_MSG = "딜러 카드: %s - 결과: %d"
    private const val PLAYER_FINAL_CARDS_MSG = "%s카드: %s - 결과: %d"
    private const val VICTORY_MSG = "## 최종 승패"
    private const val DEALER_VICTORY_MSG = "딜러: %s"
    private const val PLAYER_VICTORY_MSG = "%s: %s"

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

    fun drawFinalResults(
        dealer: FinalDealerStateDto,
        players: List<FinalPlayerStateDto>,
    ) {
        drawFinalCards(dealer, players)
        drawVictoryStatus(dealer, players)
    }

    private fun drawFinalCards(
        dealer: FinalDealerStateDto,
        players: List<FinalPlayerStateDto>,
    ) {
        println()
        println(DEALER_FINAL_CARDS_MSG.format(extractCardsState(dealer.cards), dealer.cardScore))
        players.forEach {
            println(PLAYER_FINAL_CARDS_MSG.format(it.name, extractCardsState(it.cards), it.cardScore))
        }
    }

    private fun drawVictoryStatus(
        dealer: FinalDealerStateDto,
        players: List<FinalPlayerStateDto>,
    ) {
        println()
        println(VICTORY_MSG)
        println(DEALER_VICTORY_MSG.format(extractVictoryStates(dealer.victoryStatus)))
        players.forEach {
            println(PLAYER_VICTORY_MSG.format(it.name, extractVictoryState(it.victoryStatus)))
        }
    }

    private fun extractPlayerNames(players: List<PlayerDto>): String =
        players.joinToString(", ") { it.name }

    private fun extractCardsState(cards: List<Card>): String =
        cards.joinToString(", ") { CardView.from(it) }

    private fun extractVictoryStates(states: VictoryStatues): String =
        VictoryStatusView.from(states)

    private fun extractVictoryState(state: VictoryStatus): String =
        VictoryStatusView.from(state)
}
