import domain.CardDeck
import domain.Player
import domain.Players
import ui.InputView
import ui.OutputView


private const val YES = "y"
private const val NO = "n"

fun main() {
    val playerNames = InputView.askPlayerNames()
    val players = Players(playerNames.map { Player.withName(it) })
    val cardDeck = CardDeck()
    OutputView.printGameStartMsg(playerNames)

    repeat(2 * playerNames.size) {
        val currentPlayer = players.currentPlayer()
        currentPlayer.takeCard(cardDeck.draw())
    }
    OutputView.printCardStatus(players)

    while (players.isNotEmpty()) {
        val currentPlayer = players.currentPlayer()
        val answer = getAnswer(currentPlayer)
        if (answer == YES) {
            currentPlayer.takeCard(cardDeck.draw())
            OutputView.printCardStatus(currentPlayer)
            continue;
        }
        players.quitGame(currentPlayer)
    }
}


fun getAnswer(player: Player): String {
    val answer = InputView.askDrawCardOrNot(player)
    require(answer == YES || answer == NO) { "대답은 $YES 또는 $NO 만 가능합니다" }
    return answer
}

