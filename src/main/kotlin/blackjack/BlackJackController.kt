package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.view.OutputView
import blackjack.view.OutputView.handNotice

object BlackJackController {
    fun startRound(players: Array<Player>) {
        val dealer = Dealer()
        dealer.startRound(players)
        OutputView.roundStartNotice(players)
        players.forEach(::handNotice)
        players.forEach {
            it.receiveCard(dealer.draw())
            handNotice(it)
        }
    }
}