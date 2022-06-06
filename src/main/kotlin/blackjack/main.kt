package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.view.Console
import blackjack.view.GameView
import blackjack.view.PlayerNameInputView
import blackjack.view.PlayerView
import blackjack.view.ResultView

fun main() {
    val deck = Deck.shuffled()
    val dealer = Dealer(deck)
    val console = Console()

    val playerNames = PlayerNameInputView(console).run()

    val players = dealer.startGame(playerNames)

    PlayerView(console, players).run()

    GameView(console, dealer, players).run()

    ResultView(console, players).run()
}
