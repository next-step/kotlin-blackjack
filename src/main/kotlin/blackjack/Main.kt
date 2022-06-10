package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.game.BlackJackGame
import blackjack.domain.game.TakeMoreDealer
import blackjack.domain.player.Dealer
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.ResultView
import blackjack.view.TakeMorePlayer

fun main() {

    val inputView = InputView()
    val resultView = ResultView()
    val playerNames = inputView.enterPlayerName()
    val cardDeck = CardDeck()
    val takeMorePlayer = TakeMorePlayer()
    val takeMoreDealer = TakeMoreDealer()
    val dealer = Dealer()
    val players = Players(playerNames, dealer)

    val blackJackGame = BlackJackGame(cardDeck, players)
    resultView.printInitDistributed(blackJackGame.players)

    players.playersToPlay()
        .map {
            while (it.canMoreGame() && it.wantToTake(takeMorePlayer)) {
                it.addCard(cardDeck.pickCard())
                resultView.printCardsByPlayer(it, false)
            }
        }

    blackJackGame.playDealer(takeMoreDealer)
    blackJackGame.calculateWinner()
    resultView.printCardsByPlayers(blackJackGame.players, true)
    resultView.printFinalResult(blackJackGame.players)
}
