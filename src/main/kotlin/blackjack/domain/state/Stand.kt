package blackjack.domain.state

import blackjack.domain.Cards

class Stand(cards: Cards) : Finished(cards)
