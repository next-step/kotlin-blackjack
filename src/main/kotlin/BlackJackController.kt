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

    while (participators.isGameEnd().not()) {
        val currentParticipator = participators.currentParticipator()
        if (isParticipatorStopGame(currentParticipator)) {
            participators.quitGame(currentParticipator)
            continue
        }
        currentParticipator.takeCards(cardDeck.draw())
        OutputView.printCardStatus(currentParticipator)
    }
    OutputView.printCardStatusWithResult(participators.finishParticipators())

    val winners = participators.findWinner()
    OutputView.printWinner(winners)
}

private fun isParticipatorStopGame(participator: GameParticipator): Boolean {
    if (participator is Player) {
        return participator.canDrawCard().not() ||
            InputView.askDrawCardOrNot(participator.name.name) == InputView.NO
    }
    return participator.canDrawCard().not()
}
