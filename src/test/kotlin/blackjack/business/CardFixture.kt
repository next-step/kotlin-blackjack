package blackjack.business

import blackjack.business.card.Card
import blackjack.business.card.Rank
import blackjack.business.card.Suit

object CardFixture {
    val SPACE_ACE = Card.of(Suit.SPADE, Rank.ACE)
    val SPACE_TWO = Card.of(Suit.SPADE, Rank.TWO)
    val SPACE_THREE = Card.of(Suit.SPADE, Rank.THREE)
    val SPACE_FOUR = Card.of(Suit.SPADE, Rank.FOUR)
    val SPACE_FIVE = Card.of(Suit.SPADE, Rank.FIVE)
    val SPACE_EIGHT = Card.of(Suit.SPADE, Rank.EIGHT)
    val SPACE_NINE = Card.of(Suit.SPADE, Rank.NINE)
    val SPACE_TEN = Card.of(Suit.SPADE, Rank.TEN)
}
