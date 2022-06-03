package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.game.BlackJackGame
import blackjack.domain.game.TakeMore
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerMaker
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {

    val inputView = InputView()
    val resultView = ResultView()
    val playerNames = inputView.enterPlayerName()
    val cardDeck = CardDeck()
    val takeMore = TakeMore()
    val playerMaker = PlayerMaker()
    val players = playerMaker.createPlayerByName(playerNames)
    val blackJackGame = BlackJackGame(cardDeck, players, takeMore, resultView)

    blackJackGame.start()

}
