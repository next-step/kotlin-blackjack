package blackjack.domain.card.vendor

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.player.CardHolder

interface CardVendor {
    fun drawCards(count: Int = CardHolder.INIT_CARD_COUNT): Cards

    fun drawCard(): Card
}
