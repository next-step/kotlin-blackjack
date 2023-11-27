package blackjack.model.card.pack

import blackjack.model.card.Card

interface Pack {
    fun pickCard(): Card
}
