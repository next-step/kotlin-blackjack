package blackjack.interfaces

import blackjack.Card

interface CardFactory {
    fun create(): Card
}
