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
    private val point: Int
        get() = values.sumBy { it.point }
    val isBust: Boolean
        get() = point > BLACK_JACK_NUMBER
    val isBlackjack: Boolean
        get() = point == BLACK_JACK_NUMBER

    fun with(card: Card): Cards {
        return Cards(listOf(*values.toTypedArray(), card))
    }

    companion object {
        private const val BLACK_JACK_NUMBER = 21
    }
}