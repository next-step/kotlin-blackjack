package blackjack.domain

data class Card(val suite: CardSuite, val number: CardNumber) {

    companion object {
        val CARDS = CardNumber.values()
            .flatMap { number -> generateCards(number) }
            .toSet()

        private fun generateCards(number: CardNumber) = CardSuite.values().map { suite -> Card(suite, number) }
    }
}
