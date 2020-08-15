package model

import kotlin.math.abs

class Player(val name: PlayerName) {
    private val cardMutableList: MutableList<Card> = mutableListOf()

    constructor(name: String): this(PlayerName(name))
    val cards: List<Card>
        get() = cardMutableList.toList()

    fun receive(card: Card) = cardMutableList.add(card)

    fun score(): Int = cards.sumBy { it.denomination.score }

    fun scoreGap(): Int = abs(WINNING_POINT - score())

    companion object {
        const val WINNING_POINT = 21
    }
}
