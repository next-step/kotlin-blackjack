package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.ui.InputView

fun main() {
    val dealer = Dealer()
    val players = participate()
}

private fun participate(): List<Participant> {
    return InputView.readPlayerNames()
        .map { name ->
            Player(name) { InputView.askHit(name) }
        }
}

private fun distribute(dealer: Dealer, players: List<Participant>) {
    players.forEach { player ->
        player.receive(dealer.draw())
        player.receive(dealer.draw())
    }

    dealer.receive(dealer.draw())
    dealer.receive(dealer.draw())
}
