import game.BlackJackGame
import player.Player
import view.YesOrNo
import view.askDraw
import view.inputPlayerView
import view.outputPlayerView
import view.outputResult
import view.printNameAndCard

fun main() {
    val blackJackGame = BlackJackGame.Start.startGame(inputPlayerView())
    val play = playGame(blackJackGame)
    outputResult(play.finish().getResult())
}

private fun playGame(blackJackGame: BlackJackGame.Start): BlackJackGame.Play {
    var play = blackJackGame.drawFirstTwoCards()
    outputPlayerView(play.players)
    do {
        play.getOnGoingPlayers().forEach { targetPlayer -> play = drawOrStay(play, targetPlayer) }
    } while (play.isEnd().not())
    return play
}

private fun drawOrStay(blackJackGame: BlackJackGame.Play, player: Player.OnGoing): BlackJackGame.Play {
    return when (askDraw(player.name.value)) {
        YesOrNo.YES -> blackJackGame.draw(player).also { it.getPlayerByName(player.name).printNameAndCard() }
        YesOrNo.NO -> blackJackGame.toStay(player)
    }
}
