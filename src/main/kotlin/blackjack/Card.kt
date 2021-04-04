package blackjack

data class Card(val suite: CardSuite, val number: CardNumber) {

    companion object {
        val CARDS: Map<Pair<CardSuite, CardNumber>, Card> = CardNumber.values()
            .flatMap { number -> generateCards(number) }
            .associateWith { Card(it.first, it.second) }

        val ALL_CARDS
            get() = CARDS.values.toSet()

        private fun generateCards(number: CardNumber) = CardSuite.values().map { suite -> Pair(suite, number) }

        fun from(suite: CardSuite, number: CardNumber): Card {
            return CARDS[Pair(suite, number)] ?: throw IllegalArgumentException("존재하지 않는 카드입니다.")
        }
    }
}
