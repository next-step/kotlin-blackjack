package blackjack

import blackjack.domain.GameRoom
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView

object BlackJackGame {
    fun start() {
        val names = InputView.getPlayerNames()
        val bettingMoneys = names.map { name -> InputView.getBettingMoney(name) }
        val players = getPlayers(names, bettingMoneys)
        val gameRoom = GameRoom(players = players)

        ResultView.printFirstPhase(gameRoom.participants)

        drawPlayerCards(gameRoom)
        drawDealerCards(gameRoom)

        val gameResult = gameRoom.calculateResult()
        ResultView.printFinalResult(gameResult)
    }

    private fun getPlayers(names: List<String>, bettingMoneys: List<Long>): List<Player> {
        return names.zip(bettingMoneys)
            .map { (name, bettingMoney) ->
                Player(
                    name = name,
                    bettingMoney = bettingMoney,
                )
            }
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
