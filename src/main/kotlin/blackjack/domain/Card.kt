package blackjack.domain

class Card private constructor(
    val rank: Ranks,
    val suit: Suits,
) {
    val numbers: List<Int> = rank.numbers

    override fun toString(): String = "${rank.description}${suit.description}"

    companion object {
        val ALL_CARDS = createAllCards()

        fun createCard(rank: Ranks, suit: Suits): Card {
            return ALL_CARDS[Pair(rank, suit)] ?: throw IllegalArgumentException("카드가 존재하지 않습니다.")
        }

        private fun createAllCards(): Map<Pair<Ranks, Suits>,Card> {
            return Ranks.values().flatMap { rank ->
                Suits.values().map { suit -> Card(rank, suit) }
            }.associateBy {
                Pair(it.rank, it.suit)
            }
        }
    }
}
