package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.deck.DefaultDeckGenerator
import blackjack.view.input.CreatePlayerInputView
import blackjack.view.input.PlayerTurnInputView
import blackjack.view.output.DealerDrawView
import blackjack.view.output.PlayerView
import blackjack.view.output.RecordView
import blackjack.view.output.ResultView
import blackjack.view.output.StartPlayersView

fun main() {
    val names = CreatePlayerInputView.parse()
    val blackjackGame = BlackjackGame(names, DefaultDeckGenerator())

    blackjackGame.start()
    val participantsDto = blackjackGame.getParticipants()
    StartPlayersView.print(participantsDto)

    drawPlayers(blackjackGame, names)
    drawDealer(blackjackGame)

    ResultView.print(blackjackGame.getParticipants())
    RecordView.print(blackjackGame.getRecords())
}

private fun drawPlayers(
    blackjackGame: BlackjackGame,
    names: List<String>,
) {
    names.forEach { name ->
        while (blackjackGame.canDraw(name) && PlayerTurnInputView.continueDraw(name)) {
            blackjackGame.dealCard(name)
        }
        val playerDto = blackjackGame.getParticipant(name)
        PlayerView.print(playerDto)
    }
}

private fun drawDealer(blackjackGame: BlackjackGame) {
    blackjackGame.dealCardToDealer()
    DealerDrawView.print()
}
