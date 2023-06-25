package blackjack.util

import blackjack.domain.Card

interface CardSelector {

    fun drawCard(): Card
}
