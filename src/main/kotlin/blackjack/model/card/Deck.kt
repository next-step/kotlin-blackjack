package blackjack.model.card

import blackjack.model.BlackJackGame.Companion.DEFAULT_CARD_COUNT

class Deck private constructor(private var cards: Set<Card>) {
    val size: Int
        get() = cards.size

    constructor(cards: Set<Card>, shufflingStrategy: ShufflingStrategy) : this(
        Deck(cards).shuffled(shufflingStrategy)
    )

    private fun shuffled(shufflingStrategy: ShufflingStrategy): Set<Card> = shufflingStrategy.shuffle(cards)

    fun provideCard(): Card {
        cards = resetIfEmpty()

        val cardPicked = cards.first()
        cards = cards.drop(1).toSet()
        return cardPicked
    }

    fun provideCards(count: Int = DEFAULT_CARD_COUNT): Cards =
        Cards((0 until count).map { provideCard() }.toSet())

    private fun resetIfEmpty(): Set<Card> {
        if (cards.isEmpty()) return defaultDeck().cards
        return cards
    }

    companion object {
        private val DEFAULT_DECK_CARDS = Suit.values().flatMap { cardPackOfSuit(it) }.toSet()
        fun defaultDeck() = Deck(DEFAULT_DECK_CARDS.shuffled().toSet())

        private fun cardPackOfSuit(suit: Suit): Set<Card> {
            return CardScore.values().map { cardScore -> Card(cardScore, suit) }.toSet()
        }
    }
}
