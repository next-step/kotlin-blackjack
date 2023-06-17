package blackjack.domain.behavior

import blackjack.domain.card.Card
import blackjack.domain.card.InitPlayingCards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit

val mockState = StartState(
    playingCards = InitPlayingCards(
        cards = listOf(
            Card(denomination = Denomination.ACE, suit = Suit.DIAMONDS),
            Card(denomination = Denomination.TWO, suit = Suit.DIAMONDS),
        ),
    ),
)
