package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck

class Dealer(
    val deck: CardDeck = Card.allShuffled(),
)
