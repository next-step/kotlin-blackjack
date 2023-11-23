package blackjack.domain

import blackjack.controller.InputProcessor
import blackjack.controller.ResultProcessor
import blackjack.domain.player.Players
import blackjack.domain.result.GameResult
import blackjack.domain.result.Result
import blackjack.domain.stage.CardDistributor
import blackjack.domain.stage.DealInitialCards
import blackjack.domain.stage.DistributionEnd

class BlackJackGame(
    private val inputProcessor: InputProcessor,
    private val resultProcessor: ResultProcessor = ResultProcessor(),
    val table: GameTable = GameTable(
        Dealer(),
        Players.of(inputProcessor.playerNames()) { player -> inputProcessor.playerAction(player) }
    ),
) {
    var dealCards: CardDistributor = DealInitialCards()
        private set

    fun run() {
        while (dealCards !is DistributionEnd) {
            dealCards()
        }
        emitResult(GameResult(table.players))
    }

    private fun emitResult(result: Result) {
        resultProcessor.handle(result)
    }

    fun setDistributor(distributor: CardDistributor) {
        this.dealCards = distributor
    }

    private fun dealCards() {
        val result = dealCards(table) { distributor ->  setDistributor(distributor) }
        resultProcessor.handle(result)
    }
}
