package blackjack.domain.card.vendor

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

interface CardVendor {
    fun drawPlayerFirstTwoCards(): Cards

    fun drawCard(): Card
}
