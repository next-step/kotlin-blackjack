package blackjack

import blackjack.domain.Dealer
import blackjack.domain.NormalPlayer
import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    val players = participate()
    val dealer = Dealer()

    distribution(dealer, players)
    deal(dealer, players)

    OutputView.showResult(players)
}

private fun participate(): List<NormalPlayer> {
    val names = InputView.readPlayerNames()
    return names.map { NormalPlayer(it) { InputView.askHit(it) } }
}

private fun distribution(dealer: Dealer, players: List<NormalPlayer>) {
    dealer.distribute(players)
    OutputView.showDistribution(players)
}

private fun deal(dealer: Dealer, players: List<NormalPlayer>) {
    players.forEach { player ->
        while (dealer.dealWith(player)) {
            OutputView.showHand(player)
        }
    }
}
