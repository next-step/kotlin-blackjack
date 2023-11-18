package blackjack.domain

import blackjack.controller.InputProcessor
import blackjack.controller.ResultProcessor
import blackjack.domain.player.PlayerNames
import blackjack.domain.player.Players
import blackjack.domain.result.Result
import blackjack.domain.stage.End
import blackjack.domain.stage.InitialDistribution
import blackjack.domain.stage.Stage

class BlackJackGame(
    playerNames: PlayerNames,
) {
    val players: Players = Players.from(playerNames)
    val dealer: Dealer = Dealer()
    var stage: Stage = InitialDistribution(this)

    fun run() {
        while (stage !is End) {
            progressStage()
            endStage()
        }
    }

    fun dealCardsToAllPlayers(count: Int) {
        players.allPlayers.forEach { player ->
            dealer.dealCards(player, count)
        }
    }

    fun askHitOrStand(): PlayerAction =
        InputProcessor.playerAction(players.playerInTurn)

    fun emitResult(result: Result) {
        ResultProcessor.handle(result)
    }

    private fun progressStage() {
        stage.progress()
    }

    private fun endStage() {
        stage.emitResult()
        setNextStage()
    }

    private fun setNextStage() {
        stage = stage.nextStage()
    }
}
