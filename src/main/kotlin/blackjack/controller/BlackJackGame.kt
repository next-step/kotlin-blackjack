package blackjack.controller

import blackjack.domain.DealAnswer
import blackjack.domain.DealMachine
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackJackGame {
    private val dealMachine: DealMachine = DealMachine(Deck())

    fun start(players: Players) {
        dealMachine.initialDeal(players)
        ResultView.showInitialPlayerCards(players)

        players.players.forEach {
            askDeal(it)
        }

        ResultView.showScoreResult(players)
    }

    private fun askDeal(player: Player) {
        var dealAnswer: DealAnswer = DealAnswer.YES
        var isBust = false

        while (dealAnswer.isYes() && !isBust) {
            val answer = InputView.enterDealAnswer(player)
            dealAnswer = InputView.selectDealAnswer(answer)
            dealMachine.deal(dealAnswer, player)
            ResultView.showPlayerCards(player)
            isBust = player.isBust()
        }
    }
}
