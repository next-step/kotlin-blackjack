package blackjack.model

import blackjack.model.pack.Pack

class Participants(
    val participants: Set<Player>,
    val dealer :Dealer,
) {
    fun count(): Int {
        return participants.size
    }

    fun dealing(pack: Pack) {
        participants.forEach { it.deal(pack) }
    }

    private fun isGameOver(): Boolean {
        return participants.none { Referee.isBlackJack(it) }
    }

    fun isContinue(): Boolean {
        return !isGameOver()
    }
}
