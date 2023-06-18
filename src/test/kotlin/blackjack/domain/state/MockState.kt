package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit

val mockState = Hit(
    playingCards = PlayingCards(
        cards = mutableSetOf(
            Card(denomination = Denomination.ACE, suit = Suit.DIAMONDS),
            Card(denomination = Denomination.TWO, suit = Suit.DIAMONDS),
        ),
    ),
)
