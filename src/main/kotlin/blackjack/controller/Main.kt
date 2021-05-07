package blackjack.controller

import blackjack.model.Judge
import blackjack.model.gamer.Dealer
import blackjack.model.gamer.Gamer
import blackjack.model.gamer.Gamers
import blackjack.model.gamer.PlayerBuildSource
import blackjack.model.trump.Cards
import blackjack.model.trump.Deck
import blackjack.model.trump.TrumpDeck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val deck = TrumpDeck()
    val gamers = Gamers(*InputView.readPlayerInfos().map { PlayerBuildSource(it) }.toTypedArray())
    val dealer = Dealer()

    (gamers + dealer).forEach { gamer ->
        repeat(Cards.INITIAL_DRAW_COUNT) { gamer.draw(deck) }
        OutputView.printPlayer(gamer.name, gamer.cards)
    }
    OutputView.printFirstDraw((gamers + dealer).map { it.name })

    if (dealer.drawIfNeeded(deck)) {
        OutputView.printDealerReason()

        if (dealer.isBust) {
            printJudgeResult(dealer, gamers)
            return
        }
    }

    gamers.forEach {
        drawUntilUserStop(it, deck)
    }

    (gamers + dealer).forEach {
        OutputView.printResult(it.name, it.cards, it.cards.getHighestScore())
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
    OutputView.printRevenue(dealer.name, Judge.calculateRevenue(dealer, gamers).amount)
    gamers.forEach {
        OutputView.printRevenue(it.name, Judge.calculateRevenue(it, dealer).amount)
    }
}
