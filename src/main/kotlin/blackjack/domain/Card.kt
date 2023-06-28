package blackjack.domain

import blackjack.error.BlackjackErrorMessage.NOT_EXIST_CARD

class Card private constructor(
    val rank: Ranks,
    val suit: Suits,
) {
    val numbers: List<Int> = rank.numbers

    companion object {
        val ALL_CARDS = createAllCards()

        fun createCard(rank: Ranks, suit: Suits): Card {
            return ALL_CARDS[Pair(rank, suit)] ?: throw IllegalArgumentException(NOT_EXIST_CARD)
        }

        private fun createAllCards(): Map<Pair<Ranks, Suits>, Card> {
            return Ranks.values().flatMap { rank ->
                Suits.values().map { suit -> Card(rank, suit) }
            }.associateBy {
                Pair(it.rank, it.suit)
            }
        }
    }
}
