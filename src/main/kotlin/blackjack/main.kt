package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    val names = InputView.readPlayerNames()
    val players = names.map { Player(it) { InputView.askHit(it) } }

    val dealer = Dealer()
    dealer.distribute(players)
    OutputView.showDistribution(players)

    players.forEach { player ->
        while (dealer.dealWith(player)) {
            OutputView.showHand(player)
        }
    }

    OutputView.showResult(players)
}
