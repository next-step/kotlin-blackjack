package blackjack.controller

import blackjack.model.Rule
import blackjack.model.player.Player
import blackjack.model.player.PlayersFactory
import blackjack.model.TrumpRule
import blackjack.model.player.Dealer
import blackjack.model.player.Players
import blackjack.model.trump.Cards
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val players = PlayersFactory.create(InputView.readNames())
    val dealer = Dealer(Cards.firstDraw())
    val playersAndDealer = Players.Builder().players(players + dealer).build()

    OutputView.printFirstDraw(playersAndDealer)

    players.forEach {
        drawUntilUserStop(it)
    }

    if (dealer.isDraw()) {
        OutputView.printDealerReason()
    }

    val rule = TrumpRule()
    playersAndDealer.forEach {
        OutputView.printResult(it.name, it.cards, rule.getScore(it.cards))
    }

    printJudgeResult(dealer, players, rule)
}

private fun printJudgeResult(
    dealer: Dealer,
    players: Players,
    rule: Rule
) {
    OutputView.printJudgeTitle()
    OutputView.printDealerJudgeResult(dealer.name, players.countLose(dealer, rule), players.countWin(dealer, rule))
    players.forEach {
        OutputView.printPlayerJudgeResult(it.name, it.isWin(dealer, rule))
    }
}

private fun drawUntilUserStop(player: Player) {
    while (player.keepDrawing(InputView.readUserResponse(player.name))) {
        OutputView.printPlayer(player)
    }
}
