import blackjack.GamePlayers
import blackjack.Message
import view.Input
import view.Output

fun main() {

    Output.printMessage(Message.INPUT_PLAYER_NAMES)
    val playerNames = Input.getLine()

    GamePlayers.valueOf(playerNames)
}
