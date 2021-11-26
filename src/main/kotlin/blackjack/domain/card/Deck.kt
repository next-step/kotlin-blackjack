package blackjack.domain.card

import blackjack.strategy.shuffle.CardsShuffleStrategy

@JvmInline
value class Deck private constructor(private val deck: ArrayDeque<Card>) {

    fun pop(): Card = deck.removeLast()

    companion object {
        fun initialize(cardsShuffleStrategy: CardsShuffleStrategy): Deck =
            Deck(shuffledCards(cardsShuffleStrategy))

        private fun shuffledCards(cardsShuffleStrategy: CardsShuffleStrategy): ArrayDeque<Card> =
            cardsShuffleStrategy
                .shuffle(playingCardsAllSuit())
                .toCollection(ArrayDeque())

        private fun playingCardsAllSuit(): List<Card> =
            Suit.values()
                .flatMap(::playingCardsPerSuit)

        private fun playingCardsPerSuit(suit: Suit): List<Card> =
            Denomination
                .values()
                .map { denomination -> Card(suit, denomination) }
    }
}
