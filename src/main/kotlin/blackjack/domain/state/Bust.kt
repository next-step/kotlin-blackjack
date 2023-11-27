package blackjack.domain.state

import blackjack.domain.Cards

class Bust(cards: Cards) : Finished(cards)
