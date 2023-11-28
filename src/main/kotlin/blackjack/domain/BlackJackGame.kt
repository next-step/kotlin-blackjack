package blackjack.domain

import blackjack.controller.InputProcessor
import blackjack.controller.ResultProcessor
import blackjack.domain.distirbution.CardDistributor
import blackjack.domain.distirbution.DealEnd
import blackjack.domain.distirbution.DealInitialCards
import blackjack.domain.player.Players
import blackjack.domain.result.Result

class BlackJackGame(
    private val inputProcessor: InputProcessor,
    private val resultProcessor: ResultProcessor = ResultProcessor(),
) {
    var dealCards: CardDistributor = DealInitialCards(
        GameTable(
            Dealer(),
            Players.of(inputProcessor.playerNames()) { player -> inputProcessor.playerAction(player) }
        ),
    )
        private set

    fun run() {
        var distributionCount = 0
        while (dealCards !is DealEnd && distributionCount < MAX_DISTRIBUTION_COUNT) {
            dealCards()
            distributionCount++
        }
    }

    private fun emitResult(result: Result) {
        resultProcessor.handle(result)
    }

    private fun dealCards() {
        val result = dealCards.deal()
        this.dealCards = dealCards.nextDistributor
        emitResult(result)
    }

    companion object {
        private const val MAX_DISTRIBUTION_COUNT = 20
    }
}
