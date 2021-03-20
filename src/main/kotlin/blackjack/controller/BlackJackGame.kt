package blackjack.controller

import blackjack.domain.DealAnswer
import blackjack.domain.DealMachine
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

object BlackJackGame {

    fun start(players: Players) {
        DealMachine.initialDeal(players)
        ResultView.showInitialPlayerCards(players)

        players.players.forEach {
            askDeal(it)
        }

        ResultView.showScoreResult(players)
    }

    private fun askDeal(player: Player) {
        var dealAnswer: DealAnswer = DealAnswer.YES
        var isBust = false

        while (dealAnswer == DealAnswer.YES && !isBust) {
            val answer = InputView.enterDealAnswer(player)
            dealAnswer = DealAnswer.select(answer)
            DealMachine.deal(dealAnswer, player)
            ResultView.showPlayerCards(player)
            isBust = player.isBust()
        }
    }
}