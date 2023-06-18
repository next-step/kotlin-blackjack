package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.state.finish.Blackjack
import blackjack.domain.state.finish.Bust
import blackjack.domain.state.running.Hit

val mockHitState = Hit(
    playingCards = PlayingCards(
        cards = mutableSetOf(
            Card(denomination = Denomination.ACE, suit = Suit.DIAMONDS),
            Card(denomination = Denomination.TWO, suit = Suit.DIAMONDS),
        ),
    ),
)

val mockBlackjackState = Blackjack(
    playingCards = PlayingCards(
        cards = mutableSetOf(),
    ),
)

val mockBustState = Bust(
    playingCards = PlayingCards(
        cards = mutableSetOf(),
    ),
)
