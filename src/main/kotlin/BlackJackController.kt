import domain.CardDeck
import domain.Player
import domain.Players
import ui.InputView
import ui.OutputView

fun main() {
    val playerNames = InputView.askPlayerNames()
    val players = Players(playerNames.map { Player.withName(it) })
    val cardDeck = CardDeck()
    OutputView.printGameStartMsg(playerNames)

    repeat(playerNames.size) {
        val currentPlayer = players.currentPlayer()
        currentPlayer.takeCards(cardDeck.draw(), cardDeck.draw())
    }
    OutputView.printCardStatus(players)

    val finishPlayers = mutableListOf<Player>()
    while (players.isNotEmpty()) {
        val currentPlayer = players.currentPlayer()
        if (isPlayerStopGame(currentPlayer)) {
            players.quitGame(currentPlayer)
            finishPlayers.add(currentPlayer)
            continue
        }
        currentPlayer.takeCards(cardDeck.draw())
        OutputView.printCardStatus(currentPlayer)
    }

    OutputView.printCardStatusWithResult(finishPlayers.toList())
}

private fun isPlayerStopGame(currentPlayer: Player) =
    InputView.askDrawCardOrNot(currentPlayer.name.name) == InputView.NO ||
        currentPlayer.canDrawCard().not()
