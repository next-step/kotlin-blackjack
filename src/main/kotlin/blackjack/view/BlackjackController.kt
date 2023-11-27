package blackjack.view

import blackjack.domain.BlackjackRule
import blackjack.domain.Dealer
import blackjack.domain.Player

class BlackjackController(
    inputView: InputView,
    resultView: ResultView,
) {
    init {
        val dealer = Dealer.withFullDeck()

        val players = inputView.getPlayerNames().map {
            Player(name = it, dealer = dealer)
        }

        resultView.showInitialPlayers(
            players = players,
            BlackjackRule.initialCard
        )
    }
}
