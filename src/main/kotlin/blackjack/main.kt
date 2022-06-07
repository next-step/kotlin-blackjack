package blackjack

import blackjack.domain.Croupier
import blackjack.domain.Dealer
import blackjack.domain.NormalPlayer
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    val dealer = Croupier()
    val players = listOf(dealer) + participate()

    distribution(dealer, players)
    dealWithPlayer(dealer, players)

    OutputView.showResult(players)
}

private fun participate(): List<Player> {
    val names = InputView.readPlayerNames()
    return names.map { NormalPlayer(it) { InputView.askHit(it) } }
}

private fun distribution(dealer: Dealer, players: List<Player>) {
    dealer.distribute(players)
    OutputView.showDistribution(players)
}

private fun dealWithPlayer(dealer: Dealer, players: List<Player>) {
    players.forEach { player ->
        while (dealer.dealWith(player)) {
            OutputView.showHand(player)
        }
    }
}
