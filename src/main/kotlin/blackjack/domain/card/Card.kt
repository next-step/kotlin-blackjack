package blackjack.domain.card

import java.lang.IllegalStateException
import java.util.*
import kotlin.IllegalArgumentException
import kotlin.collections.HashMap

class Card(val suit: Suit,  val denomination: Denomination) {
    val isAce: Boolean
        get() = denomination.isAce

    val score: Int
        get() = denomination.score

    val displayName: String
        get() = suit.name + denomination.name
}