import domain.CardDeck
import domain.Player
import domain.Players
import ui.InputView
import ui.OutputView


private const val INITIAL_CARD_NUMBER = 2

fun main() {
    val playerNames = InputView.askPlayerNames()
    val players = Players(playerNames.map { Player.withName(it) })
    val cardDeck = CardDeck()
    OutputView.printGameStartMsg(playerNames)

    repeat(INITIAL_CARD_NUMBER * playerNames.size) {
        val currentPlayer = players.currentPlayer()
        currentPlayer.takeCard(cardDeck.draw())
    }
    OutputView.printCardStatus(players)

    val finishPlayers = mutableListOf<Player>()
    while (players.isNotEmpty()) {
        val currentPlayer = players.currentPlayer()
        if (!currentPlayer.canDrawCard()) {
            players.quitGame(currentPlayer)
            finishPlayers.add(currentPlayer)
            continue
        }
        if (isPlayerStopGame(currentPlayer)) {
            players.quitGame(currentPlayer)
            finishPlayers.add(currentPlayer)
            continue
        }
        currentPlayer.takeCard(cardDeck.draw())
        OutputView.printCardStatus(currentPlayer)
    }

    OutputView.printCardStatus(Players(finishPlayers.toList()), showResult = true)
}

private fun isPlayerStopGame(currentPlayer: Player) = InputView.askDrawCardOrNot(currentPlayer) == InputView.NO

