package blackjack.domain.participant

import blackjack.domain.card.Cards

class Dealer(cards: Cards = Cards()) : Participant("딜러",cards)
