package blackjack.model

const val DEALER_NAME = "딜러"
const val DRAW_CONDITION = 17

class Dealer(cards: Cards = Cards(emptyList())) : Player(DEALER_NAME, cards)
