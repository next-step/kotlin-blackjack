package domain.state

import domain.card.Cards

class Stand(cards: Cards, betAmount: Int = 0) : TerminationState(cards, betAmount)
