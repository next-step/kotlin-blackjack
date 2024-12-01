package blackjack

import blackjack.domain.GameRoom
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView

object BlackJackGame {
    fun start() {
        val names = InputView.getPlayerNames()
        val players = names.map { Player(it) }
        val gameRoom = GameRoom(players = players)

        ResultView.printFirstPhase(gameRoom.participants)

        drawPlayerCards(gameRoom)
        drawDealerCards(gameRoom)

        val gameResult = gameRoom.calculateResult()
        ResultView.printFinalResult(gameResult)
    }

    private fun drawPlayerCards(gameRoom: GameRoom) {
        gameRoom.players.forEach { player -> requestDrawCards(player, gameRoom) }
    }

    private fun requestDrawCards(player: Player, gameRoom: GameRoom) {
        while (player.canDrawCard()) {
            val request = InputView.requestCard(player.name)
            if (!request) {
                break
            }
            gameRoom.drawCard(player)
            ResultView.printParticipantsCards(player)
        }
    }

    private fun drawDealerCards(gameRoom: GameRoom) {
        while (gameRoom.dealer.canDrawCard()) {
            ResultView.printDealerDrawMessage(gameRoom.dealer)
            gameRoom.drawCard(gameRoom.dealer)
        }
    }
}
