package blackjack.controller

import blackjack.model.BlackJackRule
import blackjack.model.gamer.Dealer
import blackjack.model.gamer.Gamer
import blackjack.model.gamer.Gamers
import blackjack.model.trump.Deck
import blackjack.model.trump.TrumpDeck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val deck = TrumpDeck()
    val gamers = Gamers(InputView.readNames(), deck)
    val dealer = Dealer(deck)

    OutputView.printFirstDraw(gamers + dealer)

    dealer.drawIfNeeded(deck)

    if (dealer.didOneMoreDraw()) {
        OutputView.printDealerReason()
    }

    val rule = BlackJackRule
    if (!dealer.hasValidScore(rule)) {
        printJudgeResult(dealer, gamers, rule)
        return
    }

    gamers.forEach {
        drawUntilUserStop(it, deck)
    }

    (gamers + dealer).forEach {
        OutputView.printResult(it.name, it.cards, rule.getScore(it.cards))
    }

    printJudgeResult(dealer, gamers, rule)
}

private fun drawUntilUserStop(gamer: Gamer, deck: Deck) {
    while (gamer.keepDrawing(InputView.readUserResponse(gamer.name), deck)) {
        OutputView.printPlayer(gamer)
    }
}

private fun printJudgeResult(
    dealer: Dealer,
    gamers: Gamers,
    rule: BlackJackRule
) {
    OutputView.printJudgeTitle()
    OutputView.printDealerJudgeResult(dealer.name, gamers.countLose(dealer, rule), gamers.countWin(dealer, rule))
    gamers.forEach {
        OutputView.printPlayerJudgeResult(it.name, it.isWin(dealer, rule))
    }
}
