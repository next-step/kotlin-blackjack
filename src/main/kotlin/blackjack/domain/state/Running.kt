package blackjack.domain.state

import blackjack.domain.Cards

open class Running(cards: Cards) : Started(cards)
