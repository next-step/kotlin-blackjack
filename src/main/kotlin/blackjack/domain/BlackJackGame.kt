package blackjack.domain

import blackjack.controller.InputProcessor
import blackjack.controller.ResultProcessor
import blackjack.domain.batting.BetBoard
import blackjack.domain.distirbution.CardDistributor
import blackjack.domain.distirbution.DealEnd
import blackjack.domain.distirbution.DealInitialCards
import blackjack.domain.player.Players
import blackjack.domain.result.Result
import blackjack.domain.result.distribution.DealEndResult
import blackjack.domain.result.game.GameEndResult

class BlackJackGame(
    inputProcessor: InputProcessor,
    players: Players = Players.of(inputProcessor.playerNames()) { player -> inputProcessor.playerAction(player) },
    private val resultProcessor: ResultProcessor,
) {

    var dealCards: CardDistributor = DealInitialCards(GameTable(players))
        private set

    val betBoard: BetBoard = BetBoard.of(players) { player -> inputProcessor.playerBet(player) }

    fun run() {
        var distributionCount = 0
        while (dealCards !is DealEnd && distributionCount < MAX_DISTRIBUTION_COUNT) {
            dealCards()
            distributionCount++
        }
        endDeal()
    }

    private fun emitResult(result: Result) {
        resultProcessor.handle(result)
    }

    private fun dealCards() {
        val result = dealCards.deal()
        this.dealCards = dealCards.nextDistributor
        emitResult(result)
    }

    private fun endDeal() {
        val dealEndResult = dealCards.deal() as? DealEndResult
            ?: throw IllegalArgumentException("배분이 종료되지 않았습니다")
        betBoard.closeBetting(dealEndResult)
        emitResult(GameEndResult.of(dealEndResult, betBoard))
    }

    companion object {
        private const val MAX_DISTRIBUTION_COUNT = 20
    }
}
