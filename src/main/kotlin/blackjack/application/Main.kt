package blackjack.application

import blackjack.domain.CardDeck
import blackjack.domain.shuffledDeckGenerator
import blackjack.view.BlackJackController

fun main() {
    val blackJackController = BlackJackController()

    val players = blackJackController.initPlayers()

    val blackJack = blackJackController.initBlackJack(players, CardDeck(shuffledDeckGenerator()))

    blackJackController.addCard(blackJack)

    blackJackController.result(blackJack)
}
