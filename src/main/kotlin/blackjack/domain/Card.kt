package blackjack.domain

class Card private constructor(
    val rank: Ranks,
    val suit: Suits
) {
    val numbers: List<Int> = rank.numbers

    override fun toString(): String = "${rank.description}${suit.description}"

    companion object {
        fun createCard(rank: Ranks, suit: Suits): Card {
            return Card(rank, suit)
        }
    }
}
