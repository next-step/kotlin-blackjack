package blackjack

import blackjack.domain.BlackJack.checkBurst
import blackjack.domain.Cards
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val names = InputView.getPlayersList()

    val cards = Cards()

    var players = mutableListOf<Player>()
    for (name in names) {
        players.add(Player(name))
    }

    ResultView.printDistributionPlan(players, 2)
    players.forEach {
        it.addCard(cards.takeCard(2))
        ResultView.printUserCardList(it)
    }

    players.forEach { player ->
        do {
            if (checkBurst(player)) {
                break
            }
            player.addCard(cards.takeCard(1))
            ResultView.printUserCardList(player)
        } while (InputView.isHit(player.getName()))
        ResultView.printUserCardList(player)
    }

    players.forEach {
        ResultView.printUserCardListWithResult(it)
    }
}
