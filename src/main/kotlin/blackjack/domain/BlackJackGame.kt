package blackjack.domain

import blackjack.controller.InputProcessor
import blackjack.controller.ResultProcessor
import blackjack.domain.distirbution.CardDistributor
import blackjack.domain.distirbution.DealInitialCards
import blackjack.domain.distirbution.DistributionEnd
import blackjack.domain.player.Players
import blackjack.domain.result.Result
import blackjack.domain.result.game.GameResult

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
        var distributionCount = 0
        while (dealCards !is DistributionEnd && distributionCount < MAX_DISTRIBUTION_COUNT) {
            dealCards()
            distributionCount++
        }
        emitResult(GameResult.of(table.players, table.dealer))
    }

    private fun emitResult(result: Result) {
        resultProcessor.handle(result)
    }

    fun setDistributor(distributor: CardDistributor) {
        this.dealCards = distributor
    }

    private fun dealCards() {
        val result = dealCards(table) { distributor -> setDistributor(distributor) }
        emitResult(result)
    }

    companion object {
        private const val MAX_DISTRIBUTION_COUNT = 20
    }
}
