import domain.CardDeck
import domain.GameParticipator
import domain.GameParticipators
import domain.Player
import ui.InputView
import ui.OutputView

fun main() {
    val playerNames = InputView.askPlayerNames()
    val participators = GameParticipators(playerNames.map { Player.withName(it) })
    val cardDeck = CardDeck()
    OutputView.printGameStartMsg(playerNames)

    repeat(participators.size()) {
        val currentPlayer = participators.currentParticipator()
        currentPlayer.takeCards(cardDeck.draw(), cardDeck.draw())
    }
    OutputView.printCardStatus(participators)

    val finishPlayers = mutableListOf<GameParticipator>()
    while (participators.isNotEmpty()) {
        val currentPlayer = participators.currentParticipator()
        if (isPlayerStopGame(currentPlayer)) {
            participators.quitGame(currentPlayer)
            finishPlayers.add(currentPlayer)
            continue
        }
        currentPlayer.takeCards(cardDeck.draw())
        OutputView.printCardStatus(currentPlayer)
    }

    OutputView.printCardStatusWithResult(finishPlayers.toList())
}

private fun isPlayerStopGame(currentParticipator: GameParticipator): Boolean {
    if (currentParticipator is Player) {
        return InputView.askDrawCardOrNot(currentParticipator.name.name) == InputView.NO
    }
    return currentParticipator.canDrawCard().not()
}
