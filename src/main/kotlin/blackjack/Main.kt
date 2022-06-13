package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.game.TakeMoreDealer
import blackjack.domain.game.WinnerJudge
import blackjack.domain.player.Dealer
import blackjack.domain.player.Players
import blackjack.view.CardsByPlayerView
import blackjack.view.InputView
import blackjack.view.ResultView
import blackjack.view.TakeMoreDealerView
import blackjack.view.TakeMorePlayer

fun main() {

    val inputView = InputView()
    val resultView = ResultView()
    val playerNames = inputView.enterPlayerName()
    val cardDeck = CardDeck()
    val takeMorePlayer = TakeMorePlayer()
    val cardsByPlayerView = CardsByPlayerView()
    val takeMoreDealerView = TakeMoreDealerView()
    val takeMoreDealer = TakeMoreDealer(takeMoreDealerView)
    val dealer = Dealer(cardDeck)
    val players = Players(playerNames, cardDeck)
    val blackJackGamer = players.players + dealer

    inputView.enterBattingAmountByPlayer(players.players)
    resultView.printInitDistributed(blackJackGamer)

    players.play(cardDeck, takeMorePlayer, cardsByPlayerView)
    dealer.play(cardDeck, takeMoreDealer)
    WinnerJudge(blackJackGamer)

    resultView.printCardsByPlayers(blackJackGamer, true)
    resultView.printFinalResult(blackJackGamer)
}
