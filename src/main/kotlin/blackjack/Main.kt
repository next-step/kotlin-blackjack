package blackjack

import blackjack.card.Deck
import blackjack.dealer.Dealer
import blackjack.factory.PlayerCreator
import blackjack.factory.SimpleCardCreator
import blackjack.ui.input.BlackjackInputView
import blackjack.ui.output.ResultView

fun main() {
    val playerNames = BlackjackInputView.createPlayer()
    val players = PlayerCreator.create(playerNames)
    val deck = Deck(SimpleCardCreator.startCard())
    val dealer = Dealer(deck)

    val game = BlackJackGame(players, dealer)

    game.startGame()
    ResultView.showStartStatus(
        game.allPlayer()
    )
}
