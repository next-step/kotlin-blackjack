/*
 * Copyright (c) 2021. LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package blackjack.domain


/**
 * @author tae-heon.song<taeheon.song@linecorp.com>
 * @since 2021. 04. 10.
 */
class Cards(
    private val values: List<Card>
) : List<Card> by values {
    val isBust: Boolean
        get() = calculatePoint() > BLACK_JACK_TWENTY_ONE
    val isBlackjack: Boolean
        get() = calculatePoint() == BLACK_JACK_TWENTY_ONE

    fun with(card: Card): Cards {
        return Cards(listOf(*values.toTypedArray(), card))
    }

    fun calculatePoint(): Int {
        var cardPointSum = values.sumBy { it.point }
        val aceCount = values.count { it.isAce }

        repeat(aceCount) {
            cardPointSum = changeAcePointToOneToWin(cardPointSum)
        }
        return cardPointSum
    }

    private fun changeAcePointToOneToWin(cardPointSum: Int): Int =
        if (cardPointSum > BLACK_JACK_TWENTY_ONE) cardPointSum - CardType.DECREMENTABLE_POINT_OF_ACE else cardPointSum

    companion object {
        private const val BLACK_JACK_TWENTY_ONE = 21
    }
}