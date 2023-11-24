package blackjack.model.pack

import blackjack.model.card.Card

interface Pack {
    fun pickCard(): Card
}
