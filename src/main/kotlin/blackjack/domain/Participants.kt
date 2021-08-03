package blackjack.domain

class Participants(private val participants: Set<Participant>) : Set<Participant> by participants {

    fun findWinnerScore(): Participant {
        return participants.minBy { it.calculateToFindWinner() } ?: throw IllegalStateException("플레이어가 존재하지 않습니다.")
    }

    fun makeWinners(winner: Participant) {
        participants.filter { it.isSameScore(winner) }.forEach { it.win() }
    }
}
