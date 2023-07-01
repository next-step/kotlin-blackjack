package blackjack.util

import blackjack.domain.card.Card

interface CardSelector {

    fun drawCard(): Card
}
