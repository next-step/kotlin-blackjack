package blackjack.domain.strategy

import blackjack.domain.Card

interface CardPickStrategy {
    fun pick(): Card
}
