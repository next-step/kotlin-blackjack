package blackjack

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val blackjackGame = BlackjackGame()

    val names = inputView.inputNames()
    resultView.printNames(names)

    val deck = Deck(Card.CARDS)
    val participants = blackjackGame.start(names, deck)
    resultView.printCards(participants)

    participants.forEach { participant ->
        while (inputView.inputAnswer(participant).also { blackjackGame.play(participant, deck, it) }) {
            resultView.printCards(listOf(participant))
        }
    }

    resultView.printResult(participants)
}
