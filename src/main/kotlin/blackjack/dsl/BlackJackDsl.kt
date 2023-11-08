package blackjack.dsl

import blackjack.domain.PlayerCard
import blackjack.domain.TrumpCard
import blackjack.domain.TrumpCardBuilder

object BlackJackDsl {
    fun initTrumpCard(block: TrumpCardBuilder.() -> Unit): TrumpCard {
        return TrumpCardBuilder().apply(block).build()
    }

    fun initPlayerCard(block: PlayerCard.() -> Unit): PlayerCard {
        return PlayerCard().apply(block)
    }
}
