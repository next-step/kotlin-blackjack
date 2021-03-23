package blackjack.domain

import kotlin.math.abs

class Participants(val value: List<GameParticipants>) {

    fun getWinners(): List<GameParticipants> {
        if(isDealerCardsOver21()) {
            return value.filter {
                it !is Dealer
            }
        }
        val minimumDistance = getMinimumDistance()
        return compareDistance(minimumDistance)
    }

    fun compareDistance(minimumDistance: Int): List<GameParticipants> {
        return value.filter {
            it.getDistance() == minimumDistance
        }
    }

    fun isDealerCardsOver21(): Boolean {
        return value.find {
            it is Dealer
        }!!.calculateMyCards() > 21
    }

    fun getMinimumDistance(): Int {
        return value.map {
            it.getDistance()
        }.min() ?: 0
    }
}