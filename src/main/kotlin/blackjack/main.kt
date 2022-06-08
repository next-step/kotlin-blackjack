package blackjack

import blackjack.domain.Croupier
import blackjack.domain.Dealer
import blackjack.domain.NormalPlayer
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    val dealer = Croupier()
    val players = participate()

    distribution(dealer, players)
    dealWithPlayer(dealer, players)

    OutputView.showResult(players)
}

private fun participate(): List<Player> {
    val names = InputView.readPlayerNames()
    return names.map { NormalPlayer(it) { InputView.askHit(it) } }
}

private fun distribution(dealer: Croupier, players: List<Player>) {
    players.forEach {
        it.initialize(dealer.distribute())
    }
    dealer.initialize(dealer.distribute())
    OutputView.showDistribution(dealer, players)
}

private fun dealWithPlayer(dealer: Croupier, players: List<Player>) {
    players.forEach { player ->
        dealing(dealer, player) { OutputView.showHand(player) }
    }
    dealing(dealer, dealer) { OutputView.showHand(dealer) }
}

private fun dealing(dealer: Dealer, player: Player, showHand: (Player) -> Unit) {
    while (dealer.dealWith(player)) {
        showHand(player)
    }
}
