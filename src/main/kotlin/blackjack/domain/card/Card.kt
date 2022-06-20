package blackjack.domain.card

class Card private constructor(val suit: Suit, val rank: Rank) {
    companion object {
        val CARD_PACK_CACHE: List<Card> =
            Suit.values().flatMap { suit -> Rank.values().map { rank -> Card(suit, rank) } }
    }
}
