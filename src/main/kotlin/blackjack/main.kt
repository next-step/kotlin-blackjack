package blackjack

import blackjack.domain.BLACKJACK_POINT
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    val names = InputView.readPlayerNames()
    val players = names.map { Player(it) }

    val dealer = Dealer()
    dealer.distribute(players)
    OutputView.showDistribution(players)

    players.forEach { player ->
        while (InputView.askHit(player) || player.calculate() < BLACKJACK_POINT) {
            dealer.deal(player)
            OutputView.showHand(player)
        }
    }

    OutputView.showResult(players)
}
