package baclkjack.domain.play

import baclkjack.domain.card.Card
import baclkjack.domain.card.Deck

interface User {
    val name: String
    fun start(deck: Deck)

    fun hit(deck: Deck)

    fun burst(): Boolean

    fun blackJack(): Boolean

    fun score(): Int

    fun cards(): List<Card>

    fun finish(): Boolean

    fun isDraw(): Boolean
}
