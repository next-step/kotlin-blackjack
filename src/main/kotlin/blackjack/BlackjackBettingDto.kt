package blackjack

import blackjack.domain.card.Card

class BlackjackBettingDto(val name: String, val money: Int, val cards: List<Card>, val score: Int)
