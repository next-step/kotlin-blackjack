package blackjack.model

import blackjack.model.pack.Pack

class Participants(
    val players: Set<Player>,
    val dealer: Dealer,
) {

    fun dealing(pack: Pack) {
        players.forEach { it.deal(pack) }
        dealer.play(pack)
    }

    private fun isGameOver(): Boolean {
        return players.none { Referee.isBlackJack(it) }
    }

    fun isContinue(): Boolean {
        return !isGameOver()
    }
}
