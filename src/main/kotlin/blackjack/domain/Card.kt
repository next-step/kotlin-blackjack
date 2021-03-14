package blackjack.domain

import blackjack.enums.CardShape
import blackjack.enums.CardType

class Card(
    val shape: CardShape,
    val type: CardType
) {
    val point
        get() = type.point
}
