package blackjack.controller

import blackjack.domain.result.distribution.DealInitialCardResult
import blackjack.domain.result.distribution.DealToDealerResult
import blackjack.domain.result.distribution.DealToPlayerResult
import blackjack.domain.result.game.GameResult
import blackjack.view.dto.DealerHitDto
import blackjack.view.dto.FinalDealerStateDto
import blackjack.view.dto.FinalPlayerStateDto
import blackjack.view.dto.PlayerDto
import blackjack.view.output.OutputView

object ViewResultProcessor {
    fun drawInitialDistribution(result: DealInitialCardResult) {
        val players = result.players.all.map { PlayerDto(it.name.value, it.hand.cards) }
        val dealer = PlayerDto("딜러", result.dealer.hand.cards.subList(0, 1))
        OutputView.drawInitialDistributionResult(players, dealer)
    }

    fun drawPlayerState(result: DealToPlayerResult) {
        val dto = result.player.let { PlayerDto(it.name.value, it.hand.cards) }
        OutputView.drawPlayerCurrentState(dto)
    }

    fun drawDealerState(result: DealToDealerResult) {
        OutputView.drawDealerHitStatus(DealerHitDto(result.isHit))
    }

    fun drawGameResult(result: GameResult) {
        val dealerDto =
            result.dealerResults.let {
                FinalDealerStateDto(it.dealer.hand.cards, it.dealer.score.cardScore, it.status)
            }
        val playersDto =
            result.playersResult.map {
                FinalPlayerStateDto(it.player.name.value, it.player.hand.cards, it.player.score.cardScore, it.status)
            }
        OutputView.drawFinalResults(dealerDto, playersDto)
    }
}
