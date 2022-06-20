package blackjack

import blackjack.card.Deck
import blackjack.dealer.Dealer
import blackjack.factory.PlayerCreator
import blackjack.factory.SimpleCardCreator
import blackjack.view.input.BlackjackInputView
import blackjack.view.output.ResultView

fun main() {
    val playerNames = BlackjackInputView.createPlayer()
    val players = PlayerCreator.create(playerNames)
    val deck = Deck(SimpleCardCreator.startCard())
    val dealer = Dealer(deck)

    val game = BlackJackGame(players, dealer)

    game.startGame()
    ResultView.showPlayerCard(player = players.first())
    ResultView.showPlayerCard(player = players.last())
}
