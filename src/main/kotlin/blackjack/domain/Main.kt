package blackjack.domain

import blackjack.view.Input
import blackjack.view.Output

fun main() {
    val playerNames = Input.name()
    val blackJack = BlackJack(playerNames)
    playerNames.forEach {
        blackJack.raceInit(it)
    }
    Output.initResult(blackJack.players)
    playerNames.forEach { name ->
        askingLoop(name, blackJack)
    }
    Output.result(blackJack.players)
    Output.winner(blackJack)
}

fun askingLoop(name: String, blackJack: BlackJack) {
    do {
        var askingResult = false
        if (blackJack.raceAvailable(name)) {
            askingResult = Input.race(name)
            if (askingResult) {
                blackJack.race(name)
            }
        } else {
            Output.winnerOrOver21()
        }
    } while (askingResult)
}
