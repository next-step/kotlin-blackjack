package blackjack.controller

import blackjack.model.Judge
import blackjack.model.player.Player
import blackjack.model.player.PlayersFactory
import blackjack.model.TrumpRule
import blackjack.model.player.Dealer
import blackjack.model.player.Players
import blackjack.model.trump.Cards
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.ViewUtil

fun main() {
    val players = PlayersFactory.create(InputView.readNames())
    val dealer = Dealer(Cards.firstDraw())
    val playersAndDealer = Players.Builder().players(players + dealer).build()

    OutputView.printFirstDraw(playersAndDealer)

    players.forEach {
        drawUntilUserStop(it)
    }

    if (dealer.cards.size > Cards.INITIAL_DRAW_COUNT) {
        println("딜러는 ${Dealer.MINIMUM_SCORE.value}이하라 한장의 카드를 더 받았습니다.")
    }

    val rule = TrumpRule()
    playersAndDealer.forEach {
        OutputView.printResult(it.name, it.cards, rule.getScore(it.cards))
    }

    println("## 최종 승패")
    println(
        "${dealer.name}: ${
        if (players.filter { Judge.isWin(dealer.getScore(rule), it.getScore(rule)) }.count() > 0) "${
        players.filter { Judge.isWin(dealer.getScore(rule), it.getScore(rule)) }.count()
        }승" else ""
        } ${
        if (players.filter { Judge.isWin(it.getScore(rule), dealer.getScore(rule)) }.count() > 0) "${
        players.filter { Judge.isWin(it.getScore(rule), dealer.getScore(rule)) }.count()
        }패" else ""
        }"
    )
    players.forEach {
        println("${it.name}: ${if (Judge.isWin(it.getScore(rule), dealer.getScore(rule))) "승" else "패"}")
    }
}

private fun drawUntilUserStop(player: Player) {
    while (player.keepDrawing(InputView.readUserResponse(player.name))) {
        println("${player.name}카드: ${ViewUtil.toString(player.cards)}")
    }
}
