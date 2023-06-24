package domain.state

import domain.card.BlackjackCards

class Stand(cards: BlackjackCards) : TerminationState(cards)
