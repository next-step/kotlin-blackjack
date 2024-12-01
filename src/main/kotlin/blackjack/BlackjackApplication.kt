package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.deck.DefaultDeckGenerator
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
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

    drawPlayers(blackjackGame)
    drawDealer(blackjackGame)

    ResultView.print(blackjackGame.getParticipants())
    RecordView.print(blackjackGame.getRecords())
}

private fun drawPlayers(blackjackGame: BlackjackGame) {
    val players = blackjackGame.participants.participants.filterIsInstance<Player>()
    players.forEach {
        while (blackjackGame.canDraw(it.name)) {
            if (!PlayerTurnInputView.continueDraw(it.name)) {
                break
            }
            blackjackGame.dealCard(it.name)
        }
        val playerDto = blackjackGame.getParticipant(it.name)
        PlayerView.print(playerDto)
    }
}

private fun drawDealer(blackjackGame: BlackjackGame) {
    if (blackjackGame.canDraw(blackjackGame.dealer.name)) {
        blackjackGame.dealCard(blackjackGame.dealer.name)
        DealerDrawView.print(blackjackGame.dealer.name)
    }
}
