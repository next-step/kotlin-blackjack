package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.deck.DefaultDeckGenerator
import blackjack.domain.player.Dealer
import blackjack.view.input.CreatePlayerInputView
import blackjack.view.input.PlayerTurnInputView
import blackjack.view.output.DealerDrawView
import blackjack.view.output.PlayerView
import blackjack.view.output.RecordView
import blackjack.view.output.ResultView
import blackjack.view.output.StartPlayersView

fun main() {
    val names = CreatePlayerInputView.parse()
    val dealer = Dealer()
    val blackjackGame = BlackjackGame(dealer, names, DefaultDeckGenerator())

    blackjackGame.start()
    val playersDto = blackjackGame.getParticipants()
    StartPlayersView.print(dealer, playersDto)

    drawPlayers(names, blackjackGame)
    drawDealer(blackjackGame, dealer)

    ResultView.print(blackjackGame.getParticipants())
    RecordView.print(blackjackGame.getRecords())
}

private fun drawPlayers(
    names: List<String>,
    blackjackGame: BlackjackGame,
) {
    names.forEach {
        while (blackjackGame.canDraw(it)) {
            if (!PlayerTurnInputView.continueDraw(it)) {
                break
            }
            blackjackGame.dealCard(it)
        }
        val playerDto = blackjackGame.getParticipant(it)
        PlayerView.print(playerDto)
    }
}

private fun drawDealer(
    blackjackGame: BlackjackGame,
    dealer: Dealer,
) {
    if (blackjackGame.canDraw(dealer.name)) {
        blackjackGame.dealCard(dealer.name)
        DealerDrawView.print(dealer.name)
    }
}
