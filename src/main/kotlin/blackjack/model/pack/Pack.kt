package blackjack.model.pack

import blackjack.model.Card

interface Pack {
    fun pickCard(): Card
}
