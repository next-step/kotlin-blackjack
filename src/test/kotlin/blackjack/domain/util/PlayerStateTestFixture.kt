package blackjack.domain.util

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.state.BlackJack
import blackjack.domain.player.state.Bust
import blackjack.domain.player.state.Hit
import blackjack.domain.player.state.Stay
import blackjack.domain.player.state.hands.Hands

object PlayerStateTestFixture {
    object BlackJackFixture {
        val CLUB_BLACKJACK = BlackJack(createHands(Suit.CLUB, Denomination.ACE, Denomination.JACK))
        val HEART_BLACKJACK = BlackJack(createHands(Suit.HEART, Denomination.ACE, Denomination.JACK))
        val DIAMOND_BLACKJACK = BlackJack(createHands(Suit.DIAMOND, Denomination.ACE, Denomination.JACK))
        val SPADE_BLACKJACK = BlackJack(createHands(Suit.SPADE, Denomination.ACE, Denomination.JACK))
    }

    object HitFixture {
        val CLUB_MINIMUM_HIT = Hit(createHands(Suit.CLUB, Denomination.TWO, Denomination.THREE))
        val CLUB_MAXIMUM_HIT = Hit(createHands(Suit.CLUB, Denomination.JACK, Denomination.QUEEN))
        val HEART_MINIMUM_HIT = Hit(createHands(Suit.HEART, Denomination.TWO, Denomination.THREE))
        val HEART_MAXIMUM_HIT = Hit(createHands(Suit.HEART, Denomination.JACK, Denomination.QUEEN))
        val DIAMOND_MINIMUM_HIT = Hit(createHands(Suit.DIAMOND, Denomination.TWO, Denomination.THREE))
        val DIAMOND_MAXIMUM_HIT = Hit(createHands(Suit.DIAMOND, Denomination.JACK, Denomination.QUEEN))
        val SPADE_MINIMUM_HIT = Hit(createHands(Suit.SPADE, Denomination.TWO, Denomination.THREE))
        val SPADE_MAXIMUM_HIT = Hit(createHands(Suit.SPADE, Denomination.JACK, Denomination.QUEEN))
    }

    object StayFixture {
        val CLUB_MINIMUM_STAY = Stay(createHands(Suit.CLUB, Denomination.TWO, Denomination.THREE))
        val CLUB_MAXIMUM_STAY = Stay(createHands(Suit.CLUB, Denomination.JACK, Denomination.QUEEN))
        val CLUB_DEALER_MINIMUM_STAY = Stay(createHands(Suit.CLUB, Denomination.SEVEN, Denomination.JACK))
        val HEART_MINIMUM_STAY = Stay(createHands(Suit.HEART, Denomination.TWO, Denomination.THREE))
        val HEART_MAXIMUM_STAY = Stay(createHands(Suit.HEART, Denomination.JACK, Denomination.QUEEN))
        val HEART_DEALER_MINIMUM_STAY = Stay(createHands(Suit.HEART, Denomination.SEVEN, Denomination.JACK))
        val DIAMOND_MINIMUM_STAY = Stay(createHands(Suit.DIAMOND, Denomination.TWO, Denomination.THREE))
        val DIAMOND_MAXIMUM_STAY = Stay(createHands(Suit.DIAMOND, Denomination.JACK, Denomination.QUEEN))
        val DIAMOND_DEALER_MINIMUM_STAY = Stay(createHands(Suit.DIAMOND, Denomination.SEVEN, Denomination.JACK))
        val SPADE_MINIMUM_STAY = Stay(createHands(Suit.SPADE, Denomination.TWO, Denomination.THREE))
        val SPADE_MAXIMUM_STAY = Stay(createHands(Suit.SPADE, Denomination.JACK, Denomination.QUEEN))
        val SPADE_DEALER_MINIMUM_STAY = Stay(createHands(Suit.SPADE, Denomination.SEVEN, Denomination.JACK))
    }

    object BustFixture {
        val CLUB_MINIMUM_BUST =
            Bust(createHands(Suit.CLUB, Denomination.JACK, Denomination.QUEEN, Denomination.TWO))
        val CLUB_MAXIMUM_BUST =
            Bust(createHands(Suit.SPADE, Denomination.JACK, Denomination.QUEEN, Denomination.ACE, Denomination.KING))
        val HEART_MINIMUM_BUST =
            Bust(createHands(Suit.HEART, Denomination.JACK, Denomination.QUEEN, Denomination.TWO))
        val HEART_MAXIMUM_BUST =
            Bust(createHands(Suit.SPADE, Denomination.JACK, Denomination.QUEEN, Denomination.ACE, Denomination.KING))
        val DIAMOND_MINIMUM_BUST =
            Bust(createHands(Suit.DIAMOND, Denomination.JACK, Denomination.QUEEN, Denomination.TWO))
        val DIAMOND_MAXIMUM_BUST =
            Bust(createHands(Suit.DIAMOND, Denomination.JACK, Denomination.QUEEN, Denomination.ACE, Denomination.KING))
        val SPADE_MINIMUM_BUST =
            Bust(createHands(Suit.SPADE, Denomination.JACK, Denomination.QUEEN, Denomination.TWO))
        val SPADE_MAXIMUM_BUST =
            Bust(createHands(Suit.SPADE, Denomination.JACK, Denomination.QUEEN, Denomination.ACE, Denomination.KING))
    }

    fun createHands(suit: Suit, vararg denominations: Denomination) =
        denominations.fold(Hands.EMPTY) { hands, denomination -> hands.draw(Card(suit, denomination)) }
}
