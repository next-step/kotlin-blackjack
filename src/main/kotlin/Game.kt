import blackjack.ContinueDeal
import blackjack.GameBlackjack
import blackjack.GamePlayer
import blackjack.Message
import blackjack.PlayerAction
import view.Input
import view.Output

fun main() {

    Output.printMessage(Message.INPUT_PLAYER_NAMES)
    val playerNames = Input.getLine()

    val playing = GameBlackjack.initialDealing(playerNames)
    Output.printPlayersInitialDealing(playing)

    val updatedPlayers = playing.players.map {
        var player: GamePlayer? = null
        do {
            Output.printPlayerAction(it)
            when (ContinueDeal.of(Input.getLine())) {
                ContinueDeal.YES -> {
                    player = GameBlackjack.continueDealing(it)
                    Output.printPlayerCards(player)
                    if(player.isBurst) {
                        break
                    }
                }
                ContinueDeal.NO -> break
                ContinueDeal.MISS -> continue
            }
        } while (player?.action != PlayerAction.STAND)
        player
    }
}
