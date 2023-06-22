package blackjack

import blackjack.domain.PokerCard
import blackjack.domain.builder.PokerCardBuilder

fun drawNewCard(block: PokerCardBuilder.() -> Unit): PokerCard {
    return PokerCardBuilder().apply(block).build()
}
