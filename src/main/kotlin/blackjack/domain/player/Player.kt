package blackjack.domain.player

import blackjack.domain.card.Card

class Player(val name: String, val cards: List<Card> = mutableListOf())
