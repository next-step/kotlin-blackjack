package blackjack.domain

class Participants(private val participants: Set<Participant>) : Set<Participant> by participants {

    fun findWinnerScore(): Participant {
        return participants.minBy { it.calculateToFindWinner() } ?: throw IllegalArgumentException("무승부입니다. : $this")
    }

    fun makeWinners(winner: Participant) {
        participants.filter { it.isSameScore(winner) }.forEach { it.win() }
    }
}
