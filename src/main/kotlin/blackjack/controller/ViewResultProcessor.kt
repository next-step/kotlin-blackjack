package blackjack.controller

import blackjack.domain.result.DealInitialCardResult
import blackjack.domain.result.DealToDealerResult
import blackjack.domain.result.DealToPlayerResult
import blackjack.domain.result.GameResult
import blackjack.view.dto.DealerHitDto
import blackjack.view.dto.FinalPlayerStateDto
import blackjack.view.dto.PlayerDto
import blackjack.view.output.OutputView

object ViewResultProcessor {
    fun drawInitialDistribution(result: DealInitialCardResult) {
        val players = result.players.all.map { PlayerDto(it.name.value, it.hand.cards) }
        val dealer = PlayerDto("딜러", result.dealer.hand.cards.subList(0, 1))
        OutputView.initialDistributionResult(players, dealer)
    }

    fun drawPlayerState(result: DealToPlayerResult) {
        val model = result.player.let { PlayerDto(it.name.value, it.hand.cards) }
        OutputView.playerCurrentState(model)
    }

    fun drawDealerState(result: DealToDealerResult) {
        OutputView.dealerHitStatus(DealerHitDto(result.isHit))
    }

    fun drawGameResult(result: GameResult) {
        val model =
            result.players.all.map { FinalPlayerStateDto(it.name.value, it.hand.cards, it.score.value) }
        OutputView.playerFinalStates(model)
    }
}
