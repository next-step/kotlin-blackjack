package util

import blackjack.domain.Deck
import blackjack.util.CardSelector

class FixedCardSelector(override val deck: Deck) : CardSelector()
