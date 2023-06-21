package blackjack

data class Card private constructor(
    val denomination: Denomination,
    val suit: Suit,
) {
    companion object {
        val cards = Denomination.values()
            .flatMap { number ->
                Suit.values()
                    .map { suit -> Card(number, suit) }
            }
            .associateBy { Pair(it.denomination, it.suit) }

        operator fun invoke(denomination: Denomination, suit: Suit): Card {
            return cards[Pair(denomination, suit)]
                ?: throw IllegalArgumentException("카드가 존재하지 않습니다: $denomination, $suit")
        }
    }
}
