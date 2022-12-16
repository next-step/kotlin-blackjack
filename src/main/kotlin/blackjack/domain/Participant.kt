package blackjack.domain

class Participant(
    name: String,
    cards: Cards = Cards()
) : Player(name, cards)
