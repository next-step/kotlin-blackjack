package blackjack.domain

class Participants(val value: List<GameParticipants>) {

    fun getWinners(): List<GameParticipants> {
        if (isDealerCardsOver21()) {
            return value.filter {
                it !is Dealer
            }
        }
        val filterOutOver21 = value.filter { it.calculateMyCards() <= 21 }
        return compareDistance(getMinimumDistance(filterOutOver21), filterOutOver21)
    }

    private fun compareDistance(minimumDistance: Int, participants: List<GameParticipants>): List<GameParticipants> {
        return participants.filter {
            it.getDistance() == minimumDistance
        }
    }

    private fun isDealerCardsOver21(): Boolean {
        val dealer = value.find { it is Dealer }  ?: throw IllegalStateException("딜러는 무조건 존재해야 합니다.")
        return dealer.calculateMyCards() > 21
    }

    private fun getMinimumDistance(participants: List<GameParticipants>): Int {
        return participants.map {
            it.getDistance()
        }.min() ?: 0
    }
}
