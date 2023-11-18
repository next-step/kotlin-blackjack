package blackjack.domain

import blackjack.controller.InputProcessor
import blackjack.controller.ResultProcessor
import blackjack.domain.player.Players
import blackjack.domain.result.Result
import blackjack.domain.stage.End
import blackjack.domain.stage.InitialDistribution
import blackjack.domain.stage.Stage

class BlackJackGame(
    private val inputProcessor: InputProcessor,
    private val resultProcessor: ResultProcessor = ResultProcessor(),
) {
    private val dealer: Dealer = Dealer()
    private var stage: Stage = InitialDistribution(this)
    val players: Players by lazy {
        Players.from(inputProcessor.playerNames())
    }

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
