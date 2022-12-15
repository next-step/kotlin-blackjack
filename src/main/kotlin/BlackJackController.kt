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

    val finishParticipators = mutableListOf<GameParticipator>()
    while (participators.isGameEnd()) {
        val currentParticipator = participators.currentParticipator()
        if (isParticipatorStopGame(currentParticipator)) {
            participators.quitGame(currentParticipator)
            finishParticipators.add(currentParticipator)
            continue
        }
        currentParticipator.takeCards(cardDeck.draw())
        OutputView.printCardStatus(currentParticipator)
    }

    OutputView.printCardStatusWithResult(finishParticipators.toList())
}

private fun isParticipatorStopGame(currentParticipator: GameParticipator): Boolean {
    if (currentParticipator is Player) {
        return InputView.askDrawCardOrNot(currentParticipator.name.name) == InputView.NO
    }
    return currentParticipator.canDrawCard().not()
}
