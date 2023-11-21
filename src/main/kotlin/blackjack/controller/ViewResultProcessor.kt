package blackjack.controller

import blackjack.domain.result.DetermineWinnerResult
import blackjack.domain.result.InGameResult
import blackjack.domain.result.InitialDistributionResult
import blackjack.view.dto.FinalPlayerStateDto
import blackjack.view.dto.PlayerDto
import blackjack.view.output.OutputView

object ViewResultProcessor {
    fun drawInitialDistribution(result: InitialDistributionResult) {
        val model = result.players.allPlayers.map { PlayerDto(it.name.value, it.hand.cards) }
        OutputView.initialDistributionResult(model)
    }

    fun drawPlayerState(result: InGameResult) {
        val model = result.player.let { PlayerDto(it.name.value, it.hand.cards) }
        OutputView.playerCurrentState(model)
    }

    fun drawGameResult(result: DetermineWinnerResult) {
        val model =
            result.players.allPlayers.map { FinalPlayerStateDto(it.name.value, it.hand.cards, it.score.value) }
        OutputView.playerFinalStates(model)
    }
}
