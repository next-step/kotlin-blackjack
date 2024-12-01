package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.deck.DefaultDeckGenerator
import blackjack.view.dto.CreatePlayersDto
import blackjack.view.input.CreatePlayerInputView
import blackjack.view.input.PlayerTurnInputView
import blackjack.view.output.DealerDrawView
import blackjack.view.output.PlayerView
import blackjack.view.output.RecordView
import blackjack.view.output.ResultView
import blackjack.view.output.StartPlayersView

fun main() {
    val createPlayersDto = CreatePlayerInputView.parse()
    val blackjackGame = BlackjackGame(createPlayersDto, DefaultDeckGenerator())

    blackjackGame.start()
    val participantsDto = blackjackGame.getParticipants()
    StartPlayersView.print(participantsDto)

    drawPlayers(blackjackGame, createPlayersDto)
    drawDealer(blackjackGame)

    ResultView.print(blackjackGame.getParticipants())
    RecordView.print(blackjackGame.getRecords())
}

private fun drawPlayers(
    blackjackGame: BlackjackGame,
    dto: List<CreatePlayersDto>,
) {
    dto.forEach {
        while (blackjackGame.canDraw(it.name) && PlayerTurnInputView.continueDraw(it.name)) {
            blackjackGame.dealCard(it.name)
        }
        val playerDto = blackjackGame.getParticipant(it.name)
        PlayerView.print(playerDto)
    }
}

private fun drawDealer(blackjackGame: BlackjackGame) {
    blackjackGame.dealCardToDealer()
    DealerDrawView.print()
}
