package blackjack.model

class Participants(
    val participants: Set<Player>,
) {
    fun count(): Int {
        return participants.size
    }

    fun dealing() {
        participants.forEach { it.deal() }
    }

    private fun isGameOver(): Boolean {
        return participants
            .any { Referee.isBlackJack(it) }
    }

    fun isContinue(): Boolean {
        return !isGameOver()
    }
}
