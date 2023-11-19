package blackjack.model

import blackjack.model.pack.Pack

class Participants(
    val players: Set<Player>,
    val dealer :Dealer,
) {
    fun count(): Int {
        return players.size
    }

    fun dealing(pack: Pack) {
        players.forEach { it.deal(pack) }
    }

    private fun isGameOver(): Boolean {
        return players.none { Referee.isBlackJack(it) }
    }

    fun isContinue(): Boolean {
        return !isGameOver()
    }
}
