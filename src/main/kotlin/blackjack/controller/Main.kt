package blackjack.controller

import blackjack.model.BlackJackRule
import blackjack.model.Judge
import blackjack.model.gamer.Dealer
import blackjack.model.gamer.Gamer
import blackjack.model.gamer.Gamers
import blackjack.model.gamer.PlayerBuildSource
import blackjack.model.trump.Deck
import blackjack.model.trump.TrumpDeck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val deck = TrumpDeck()
    val gamers = Gamers(InputView.readPlayerInfos().map { PlayerBuildSource(it) }, deck)
    val dealer = Dealer(deck)

    OutputView.printFirstDraw((gamers + dealer).map { it.name })
    (gamers + dealer).forEach {
        OutputView.printPlayer(it.name, it.cards)
    }

    gamers.forEach {
        drawUntilUserStop(it, deck)
    }

    dealer.drawIfNeeded(deck)
    if (dealer.didOneMoreDraw()) {
        OutputView.printDealerReason()
    }

    if (!dealer.hasValidScore(BlackJackRule)) {
        printJudgeResult(dealer, gamers)
        return
    }

    (gamers + dealer).forEach {
        OutputView.printResult(it.name, it.cards, BlackJackRule.getScore(it.cards))
    }
    printJudgeResult(dealer, gamers)
}

private fun drawUntilUserStop(gamer: Gamer, deck: Deck) {
    while (gamer.keepDrawing(InputView.readUserResponse(gamer.name), deck)) {
        OutputView.printPlayer(gamer.name, gamer.cards)
    }
}

private fun printJudgeResult(
    dealer: Dealer,
    gamers: Gamers
) {
    OutputView.printJudgeTitle()
    OutputView.printRevenue(dealer.name, - Judge.calculateRevenue(gamers, dealer, BlackJackRule).amount)
    gamers.forEach {
        OutputView.printRevenue(it.name, Judge.calculateRevenue(BlackJackRule.getScore(it.cards), BlackJackRule.getScore(dealer.cards), it.betMoney).amount)
    }
}
