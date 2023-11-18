package blackjack.domain

import blackjack.controller.ResultProcessor
import blackjack.domain.player.PlayerNames
import blackjack.domain.player.Players
import blackjack.domain.result.Result
import blackjack.domain.stage.InitialDistribution
import blackjack.domain.stage.Stage

class BlackJackGame(
    playerNames: PlayerNames,
) {
    val players: Players = Players.from(playerNames)
    val dealer: Dealer = Dealer()
    var stage: Stage = InitialDistribution(this)

    fun run() {
        progressStage()
        handleResult()
    }

    fun dealCardsToAllPlayers(count: Int) {
        players.allPlayers.forEach { player ->
            dealer.dealCards(player, count)
        }
    }

    fun emitResult(result: Result) {
        ResultProcessor.handle(result)
    }

    private fun progressStage() {
        stage.progress()
    }

    private fun handleResult() {
        stage.handleResult()
    }

    private fun setNextStage() {
        stage = stage.nextStage()
    }
}
