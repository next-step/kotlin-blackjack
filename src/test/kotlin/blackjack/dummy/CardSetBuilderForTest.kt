package blackjack.dummy

import blackjack.model.card.Card
import blackjack.model.card.CardShape
import blackjack.model.card.Cards
import blackjack.model.card.Denomination
import java.util.Locale

// 10S == 10 + SPADES
// A2234~10JQK
// S D H C
fun String.toCardSet() = Cards(this.split(",").mapNotNull { it.parseToCard() })

fun String.parseToCard(): Card? {
    val nameKey = (
        if (this.startsWith("10"))
            this.substring(0, 2)
        else
            this.substring(0, 1)

        ).uppercase(Locale.getDefault())

    val shapeKey = (
        if (this.startsWith("10"))
            this.substring(2, 3)
        else
            this.substring(1, 2)
        ).uppercase(Locale.getDefault())

    val denomination = Denomination.values().find { it.displayName == nameKey } ?: return null
    val shape = when (shapeKey) {
        "S" -> CardShape.SPADES
        "D" -> CardShape.DIAMONDS
        "H" -> CardShape.HEARTS
        "C" -> CardShape.CLUBS
        else -> null
    } ?: return null

    return Card.of(denomination, shape)
}
