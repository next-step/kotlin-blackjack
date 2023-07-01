package blackjack

import blackjack.controller.Blackjack
import blackjack.view.ConsoleView

fun main() {
    val gameCardStorage = GameCardStorage(prepareAllPossibleRandomUniqueCards())

    val blackjack = Blackjack(
        inputView = ConsoleView,
        outputView = ConsoleView
    )

    blackjack.play(gameCardStorage)
}

fun prepareAllPossibleRandomUniqueCards() = CardNumber
    .values()
    .flatMap { number ->
        Suit.values().map { suit ->
            Card(suit, number)
        }
    }
    .shuffled()
    .toSet()
