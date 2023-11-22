package blackjack.domain

import blackjack.controller.InputProcessor
import blackjack.controller.ResultProcessor
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.result.GameResult
import blackjack.domain.result.Result
import blackjack.domain.stage.CardDistributor
import blackjack.domain.stage.DealInitialCards
import blackjack.domain.stage.DistributionEnd

class BlackJackGame(
    private val inputProcessor: InputProcessor,
    private val resultProcessor: ResultProcessor = ResultProcessor(),
    val players: Players = Players.from(inputProcessor.playerNames()),
    val dealer: Dealer = Dealer()
) {
    var dealCards: CardDistributor = DealInitialCards()

    val isPlayerInTurnScoreOverMax: Boolean
        get() = players.isPlayerInTurnOverMaxScore

    val playerInTurn: Player
        get() = players.playerInTurn

    val isLastTurn: Boolean
        get() = players.isLastTurn

    fun run() {
        while (dealCards !is DistributionEnd) {
            dealCards()
        }
        emitResult(GameResult(players))
    }

    fun dealCardsToAllPlayers(count: Int) {
        players.allPlayers.forEach { player ->
            dealer.dealCards(player, count)
        }
    }

    fun dealCardToDealer(count: Int) {
        dealer.dealToSelf(count)
    }

    fun dealCardToPlayerInTurn() {
        dealer.dealCards(players.playerInTurn, 1)
    }

    fun passTurnToNextPlayer() {
        players.changePlayer()
    }

    fun askHitOrStand(): PlayerAction = inputProcessor
        .playerAction(players.playerInTurn)

    private fun emitResult(result: Result) {
        resultProcessor.handle(result)
    }

    fun setDistributor(distributor: CardDistributor) {
        this.dealCards = distributor
    }

    private fun dealCards() {
        val result = dealCards(this)
        resultProcessor.handle(result)
    }
}
