package blackjack.domain.player

import blackjack.domain.Hands
import blackjack.domain.card.Card

interface Participant {

    fun name(): String

    fun draw(card: Card)

    fun stay()

    fun isFinished(): Boolean

    fun cards(): Set<Card>

    fun hands(): Hands

    fun score(): Int
}
