package blackjack

import blackjack.domain.PokerCard
import blackjack.domain.builder.PokerCardBuilder

fun pokerCardOf(block: PokerCardBuilder.() -> Unit): PokerCard {
    return PokerCardBuilder().apply(block).build()
}
