package blackjack.controller

import blackjack.domain.result.distribution.DealInitialCardResult
import blackjack.domain.result.distribution.DealToDealerResult
import blackjack.domain.result.distribution.DealToPlayerResult
import blackjack.domain.result.game.GameEndResult
import blackjack.view.dto.DealerCardsResultDto
import blackjack.view.dto.DealerHitDto
import blackjack.view.dto.DealerProfitDto
import blackjack.view.dto.PlayerCardsResultDto
import blackjack.view.dto.PlayerDto
import blackjack.view.dto.PlayerProfitDto
import blackjack.view.output.OutputView

object ViewResultProcessor {
    fun drawInitialDistribution(result: DealInitialCardResult) {
        val players = result.players.value.map { PlayerDto(it.name.value, it.hand.cards) }
        val dealer = PlayerDto("딜러", result.dealer.hand.cards.subList(0, 1))
        OutputView.drawInitialDistributionResult(players, dealer)
    }

    fun drawPlayerState(result: DealToPlayerResult) {
        if (result.isSystemStand) return
        val dto = result.player.let { PlayerDto(it.name.value, it.hand.cards) }
        OutputView.drawPlayerCurrentState(dto)
    }

    fun drawDealerState(result: DealToDealerResult) {
        OutputView.drawDealerHitStatus(DealerHitDto(result.isHit))
    }

    fun drawGameResult(result: GameEndResult) {
        drawCardResult(result)
        drawProfitResult(result)
    }

    private fun drawCardResult(result: GameEndResult) {
        val dealerDto = DealerCardsResultDto(result.dealerHand.cards, result.dealerScore.value)
        val playersDto = result.playerResults
            .map { PlayerCardsResultDto(it.name.value, it.hand.cards, it.score.value) }
        OutputView.drawCardsResults(dealerDto, playersDto)
    }

    private fun drawProfitResult(result: GameEndResult) {
        val dealerDto =
            DealerProfitDto(result.dealerProfit.value.toString())
        val playersDto = result.playerResults.map {
            PlayerProfitDto(it.name.value, it.profit.value.toString())
        }
        OutputView.drawProfitResults(dealerDto, playersDto)
    }
}
