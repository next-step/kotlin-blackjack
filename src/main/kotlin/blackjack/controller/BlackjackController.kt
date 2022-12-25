package blackjack.controller

import blackjack.domain.Blackjack
import blackjack.view.BlackjackView
import blackjack.view.input.BlackjackInputReader

class BlackjackController(
    private val blackjackView: BlackjackView,
    private val blackjackInputReader: BlackjackInputReader,
) {
    fun run() {
        val playerNames = blackjackInputReader.readPlayerNames()

        val blackjack = Blackjack.of(playerNames)

        blackjackView.printlnInitialPlayersCards(blackjack)

        blackjackView.printlnBlackjack(blackjack)

        blackjackInputReader.readPlayersHitOrStay(blackjack, blackjack::hitOrStay)

        blackjackView.printlnBlackjackResult(blackjack)
    }
}
