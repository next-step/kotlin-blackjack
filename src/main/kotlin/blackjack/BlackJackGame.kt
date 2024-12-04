package blackjack

import blackjack.domain.BettingMoney
import blackjack.domain.GameRoom
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView

object BlackJackGame {
    fun start() {
        val names = InputView.getPlayerNames()
        val players = names.map { name -> getPlayer(name) }
        val gameRoom = GameRoom(players = players)

        ResultView.printFirstPhase(gameRoom.participants)

        drawPlayerCards(gameRoom)
        drawDealerCards(gameRoom)

        val gameResult = gameRoom.calculateResult()
        ResultView.printFinalResult(gameResult)
    }

    private fun getPlayer(name: String): Player {
        val bettingMoney = InputView.getBettingMoney(name)
        return Player(
            name = name,
            bettingMoney = BettingMoney(bettingMoney),
        )
    }

    private fun drawPlayerCards(gameRoom: GameRoom) {
        gameRoom.players.forEach { player -> requestDrawCards(player, gameRoom) }
    }

    private fun requestDrawCards(player: Player, gameRoom: GameRoom) {
        while (shouldDrawCard(player)) {
            gameRoom.drawCard(player)
            ResultView.printParticipantsCards(player)
        }
    }

    private fun shouldDrawCard(player: Player): Boolean {
        return player.canDrawCard() && InputView.requestCard(player.name)
    }

    private fun drawDealerCards(gameRoom: GameRoom) {
        while (gameRoom.dealer.canDrawCard()) {
            ResultView.printDealerDrawMessage(gameRoom.dealer)
            gameRoom.drawCard(gameRoom.dealer)
        }
    }
}
