package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.game.TakeMoreDealer
import blackjack.domain.game.WinnerJudge
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
    val dealer = Dealer(cardDeck)
    val players = Players(playerNames, cardDeck)
    val blackJackGamer = players.players + dealer

    inputView.enterBattingAmountByPlayer(players.players)
    resultView.printInitDistributed(blackJackGamer)

    players.playersToPlay()
        .map {
            while (it.canMoreGame() && it.wantToTake(takeMorePlayer)) {
                it.addCard(cardDeck.pickCard())
                resultView.printCardsByPlayer(it, false)
            }
        }

    dealer.play(cardDeck, takeMoreDealer)
    WinnerJudge(blackJackGamer)

    resultView.printCardsByPlayers(blackJackGamer, true)
    resultView.printFinalResult(blackJackGamer)
}
