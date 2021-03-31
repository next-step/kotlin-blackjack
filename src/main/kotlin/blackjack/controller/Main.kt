package blackjack.controller

import blackjack.model.player.Player
import blackjack.model.TrumpRule
import blackjack.model.player.Dealer
import blackjack.model.player.Players
import blackjack.model.trump.Deck
import blackjack.model.trump.TrumpDeck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val deck = TrumpDeck()
    val players = Players(InputView.readNames(), deck)
    val dealer = Dealer(deck)
    val playersAndDealer = Players((players + dealer).toList())

    OutputView.printFirstDraw(playersAndDealer)

    val rule = TrumpRule
    if (!dealer.hasValidScore(rule)) {
        printJudgeResult(dealer, players, rule)
        return
    }

    players.forEach {
        drawUntilUserStop(it, deck)
    }

    if (dealer.didOneMoreDraw()) {
        OutputView.printDealerReason()
    }

    playersAndDealer.forEach {
        OutputView.printResult(it.name, it.cards, rule.getScore(it.cards))
    }

    printJudgeResult(dealer, players, rule)
}

private fun drawUntilUserStop(player: Player, deck: Deck) {
    while (player.keepDrawing(InputView.readUserResponse(player.name), deck)) {
        OutputView.printPlayer(player)
    }
}

private fun printJudgeResult(
    dealer: Dealer,
    players: Players,
    rule: TrumpRule
) {
    OutputView.printJudgeTitle()
    OutputView.printDealerJudgeResult(dealer.name, players.countLose(dealer, rule), players.countWin(dealer, rule))
    players.forEach {
        OutputView.printPlayerJudgeResult(it.name, it.isWin(dealer, rule))
    }
}
