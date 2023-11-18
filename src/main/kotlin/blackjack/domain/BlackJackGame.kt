package blackjack.domain

import blackjack.controller.InputProcessor
import blackjack.controller.ResultProcessor
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.result.Result
import blackjack.domain.stage.InitialDistribution
import blackjack.domain.stage.Stage

class BlackJackGame(
    private val inputProcessor: InputProcessor,
    private val resultProcessor: ResultProcessor = ResultProcessor(),
    val players: Players = Players.from(inputProcessor.playerNames())
) {
    val dealer: Dealer = Dealer()
    var stage: Stage = InitialDistribution(this)

    val isPlayerInTurnScoreOverMax: Boolean
        get() = players.isPlayerInTurnOverMaxScore

    val playerInTurn : Player
        get() = players.playerInTurn

    fun run() {
        progressStage()
        endStage()
    }

    fun dealCardsToAllPlayers(count: Int) {
        players.allPlayers.forEach { player ->
            dealer.dealCards(player, count)
        }
    }

    fun dealCardToPlayerInTurn() {
        dealer.dealCards(players.playerInTurn, 1)
    }

    fun askHitOrStand(): PlayerAction = inputProcessor
        .playerAction(players.playerInTurn)

    fun emitResult(result: Result) {
        resultProcessor.handle(result)
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
