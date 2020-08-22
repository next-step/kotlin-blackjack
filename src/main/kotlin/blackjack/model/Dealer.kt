package blackjack.model

const val DEALER_NAME = "Dealer"

class Dealer(cards: Cards = Cards(emptyList())) : Player(DEALER_NAME, cards)
