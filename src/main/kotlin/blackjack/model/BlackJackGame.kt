package blackjack.model

import blackjack.model.card.CardDeck
import blackjack.model.player.Players
import blackjack.view.OutputView

class BlackJackGame(private val players: Players) {
    private val cardDeck = CardDeck()

    fun firstTurn() {
        OutputView.firstTurn(players)

        players.drawCard(cardDeck)
        players.drawCard(cardDeck)

        OutputView.printCardForPlayers(players)
    }

    fun progressTurn() {
        players.progressTurn(cardDeck)
    }

    fun checkWinOrLose() {
        players.checkWinOrLose()
    }
}
