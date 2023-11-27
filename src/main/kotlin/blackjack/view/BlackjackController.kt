package blackjack.view

import blackjack.domain.BlackjackRule
import blackjack.domain.Deck
import blackjack.domain.Player

class BlackjackController(
    inputView: InputView,
    resultView: ResultView,
) {
    init {
        val deck = Deck.forBlackjack()

        val players = inputView.getPlayerNames().map {
            Player(name = it, deck.popMany(count = BlackjackRule.initialCard))
        }

        resultView.showInitialPlayers(
            players = players,
            BlackjackRule.initialCard
        )
    }
}
