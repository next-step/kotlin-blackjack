package blackjack.common.domain.builder

import blackjack.common.domain.PokerCard

fun pokerCardOf(block: PokerCardBuilder.() -> Unit): PokerCard {
    return PokerCardBuilder().apply(block).build()
}
