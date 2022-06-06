package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.view.Console
import blackjack.view.DealerGameView
import blackjack.view.PlayerGameView
import blackjack.view.PlayerNameInputView
import blackjack.view.PlayerView
import blackjack.view.ResultView
import blackjack.view.WinnerView

fun main() {
    val deck = Deck.shuffled()
    val dealer = Dealer(deck)
    val console = Console()

    val playerNames = PlayerNameInputView(console).run()

    val players = dealer.startGame(playerNames)

    PlayerView(console, dealer, players).run()
    PlayerGameView(console, dealer, players).run()
    DealerGameView(console, dealer).run()
    ResultView(console, dealer, players).run()
    WinnerView(console, dealer, players).run()
}
