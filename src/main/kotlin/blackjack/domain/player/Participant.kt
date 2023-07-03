package blackjack.domain.player

import blackjack.domain.card.Card

interface Participant {

    fun name(): String

    fun draw(card: Card)

    fun stay()

    fun isFinished(): Boolean

    fun cards(): Set<Card>

    fun score(): Int

    fun competeWith(participant: Participant): Int
}
