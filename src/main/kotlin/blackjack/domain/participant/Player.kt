package blackjack.domain.participant

import blackjack.domain.card.Cards

class Player(
    name: String,
    cards: Cards = Cards(),
) : Participant(name, cards)
