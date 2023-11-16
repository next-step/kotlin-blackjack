package blackjack.controller

import blackjack.domain.player.PlayerNames
import blackjack.domain.result.InitialDistributionResult
import blackjack.domain.stage.InitialDistribution
import blackjack.view.input.InputView
import blackjack.view.model.PlayerView
import blackjack.view.output.OutputView

object InputProcessor {
    val playerNames: PlayerNames
        get() = InputView.playerNames().let(PlayerNames::of)

    fun InitialDistribution.returnResult(result: InitialDistributionResult) {
        val model = result.players.allPlayers.map { PlayerView(it.name.value, it.hand.cards) }
        OutputView.initialDistributionResult(model)
    }
}
