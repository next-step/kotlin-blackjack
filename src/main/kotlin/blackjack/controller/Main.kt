package blackjack.controller

import blackjack.model.BlackJackRule
import blackjack.model.Judge
import blackjack.model.Rule
import blackjack.model.gamer.Dealer
import blackjack.model.gamer.Gamer
import blackjack.model.gamer.Gamers
import blackjack.model.trump.Deck
import blackjack.model.trump.TrumpDeck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val deck = TrumpDeck()
    val gamers = Gamers(InputView.readPlayerInfos(), deck)
    val dealer = Dealer(deck)

    OutputView.printFirstDraw(gamers + dealer)

    val rule = BlackJackRule

    gamers.forEach {
        drawUntilUserStop(it, deck)
    }

    dealer.drawIfNeeded(deck)
    if (dealer.didOneMoreDraw()) {
        OutputView.printDealerReason()
    }

    if (!dealer.hasValidScore(rule)) {
        printJudgeResult(dealer, gamers, rule)
        return
    }

    OutputView.printResults(gamers + dealer, rule)
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
    rule: Rule
) {
    OutputView.printJudgeTitle()
    OutputView.printRevenue(
        dealer.name,
        - gamers.fold(0) { acc, gamer ->
            acc + Judge.calculateRevenue(
                rule.getScore(gamer.cards),
                rule.getScore(dealer.cards),
                gamer.bet
            )
        }
    )
    gamers.forEach {
        OutputView.printRevenue(it.name, Judge.calculateRevenue(rule.getScore(it.cards), rule.getScore(dealer.cards), it.bet))
    }
}
