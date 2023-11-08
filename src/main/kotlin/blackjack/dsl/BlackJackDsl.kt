package blackjack.dsl

import blackjack.domain.TrumpCard
import blackjack.domain.TrumpCardBuilder

object BlackJackDsl {
    fun initTrumpCard(block: TrumpCardBuilder.() -> Unit): TrumpCard {
        return TrumpCardBuilder().apply(block).build()
    }
}