package controller

import domain.gamer.Gamers
import domain.gamer.dealer.Dealer
import domain.gamer.player.Player
import domain.gamer.player.Players
import domain.turn.Game
import domain.turn.InitialTurn
import presentation.InputView
import presentation.ResultView

fun main() {
    val playerNames = InputView.getPlayerNames()
    val players = Players(playerNames.map { Player(it) })
    val dealer = Dealer()

    val game = Game(InitialTurn, Gamers.of(dealer, players))
    game.proceed()
    ResultView.printInitialState(dealer, players.list)
    dealer.addOnHitCallback {
        ResultView.printDealerReceiveCardMessage()
    }

    while (true) {
        askUntilNeedToHit(game)
        game.proceed()

        if (game.isFinish) {
            game.result?.let {
                ResultView.printResult(it)
            }
            break
        }

        ResultView.printResult(dealer, players.list)
    }
}

private fun askUntilNeedToHit(game: Game) {
    while (true) {
        val gamer = game.gamerToHit() ?: break
        if (gamer !is Player) break
        if (!askPlayerWantToStay(gamer)) break
        gamer.stay()
    }
}

private fun askPlayerWantToStay(player: Player): Boolean {
    return !InputView.askReceiveCard(player.name)
}
