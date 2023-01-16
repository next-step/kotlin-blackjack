package blackjack.domain.participantion

import blackjack.domain.card.Cards

class Player(name: String, cards: Cards, price: Price) : Participant(name, cards, price)
