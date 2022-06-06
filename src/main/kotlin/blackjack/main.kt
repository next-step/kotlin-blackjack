package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.view.Console
import blackjack.view.PlayerGameView
import blackjack.view.PlayerNameInputView
import blackjack.view.PlayerView
import blackjack.view.ResultView

fun main() {
    val deck = Deck.shuffled()
    val dealer = Dealer(deck)
    val console = Console()

    val playerNames = PlayerNameInputView(console).run()

    val players = dealer.startGame(playerNames)

    PlayerView(console, dealer, players).run()

    PlayerGameView(console, dealer, players).run()

    ResultView(console, players).run()
}
