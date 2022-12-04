package blackjack.strategy

import blackjack.Card

interface CardPickStrategy {
    fun pick(): Card
}
