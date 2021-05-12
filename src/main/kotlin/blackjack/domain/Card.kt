package blackjack.domain

import blackjack.view.CardSuite

data class Card(val suite: CardSuite, val number: CardNumber) {

    companion object {
        val CARDS = CardNumber.values()
            .flatMap { number -> generateCards(number) }
            .shuffled()
            .toSet()

        private fun generateCards(number: CardNumber) = CardSuite.values().map { suite -> Card(suite, number) }
    }
}
