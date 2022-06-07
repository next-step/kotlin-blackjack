package blackjack

import blackjack.domain.Croupier
import blackjack.domain.NormalPlayer
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    val players = participate()
    val dealer = Croupier()

    distribution(dealer, players)
    deal(dealer, players)

    OutputView.showResult(players)
}

private fun participate(): List<Player> {
    val names = InputView.readPlayerNames()
    return names.map { NormalPlayer(it) { InputView.askHit(it) } }
}

private fun distribution(dealer: Croupier, players: List<Player>) {
    dealer.distribute(players)
    OutputView.showDistribution(players)
}

private fun deal(dealer: Croupier, players: List<Player>) {
    players.forEach { player ->
        while (dealer.dealWith(player)) {
            OutputView.showHand(player)
        }
    }
}
