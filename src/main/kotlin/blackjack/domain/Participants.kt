package blackjack.domain

class Participants(val value: List<GameParticipants>) {

    fun getWinners(): List<GameParticipants> {
        if (isDealerCardsOver21()) {
            return value.filter {
                it !is Dealer
            }
        }
        val minimumDistance = getMinimumDistance()
        return compareDistance(minimumDistance)
    }

    private fun compareDistance(minimumDistance: Int): List<GameParticipants> {
        return value.filter {
            it.getDistance() == minimumDistance
        }
    }

    private fun isDealerCardsOver21(): Boolean {
        return value.find {
            it is Dealer
        }!!.calculateMyCards() > 21
    }

    private fun getMinimumDistance(): Int {
        return value.map {
            it.getDistance()
        }.min() ?: 0
    }
}
