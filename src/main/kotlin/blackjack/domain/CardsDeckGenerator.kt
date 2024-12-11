package blackjack.domain

class CardsDeckGenerator : CardsGenerator {
    override fun generate(): List<Card> =
        CardNumber.entries
            .flatMap { number ->
                CardSuit.entries.map { suit -> Card(number, suit) }
            }.shuffled()
}
