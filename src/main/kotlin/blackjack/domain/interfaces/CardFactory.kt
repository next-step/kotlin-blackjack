package blackjack.domain.interfaces

import blackjack.domain.Card

interface CardFactory {
    fun create(): Card
}
