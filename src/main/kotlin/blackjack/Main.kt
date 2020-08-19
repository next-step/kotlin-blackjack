package blackjack

import blackjack.domain.Participants
import blackjack.domain.deck.Deck
import blackjack.view.InputView
import blackjack.view.ResultView

const val FIRST_SETTING_CARD_NUMBER = 2

fun main() {
    val participants = getParticipants()
    playGame(participants)
}

private fun getParticipants(): Participants {
    val playerNames = InputView.getPlayers()
    return Participants(playerNames)
}

private fun playGame(participants: Participants) {
    Deck.reset()
    participants.addCards(FIRST_SETTING_CARD_NUMBER)
    ResultView.printPlayersCard(participants)

    playBlackjack(participants)
    ResultView.printResult(participants)
}

fun playBlackjack(participants: Participants) {
    participants.players.forEach {
        while (it.isGaming()) {
            val yesOrNo = InputView.getMoreCard(it)
            it.choose(yesOrNo)
        }
        ResultView.printPlayerCard(it)
    }
}
