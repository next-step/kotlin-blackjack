package blackjack.domain.card

import blackjack.domain.card.Card

interface DrawStrategy {

    fun fetchCard(): Card

    companion object {
        const val DEAL_DRAW_COUNT = 2
    }
}
