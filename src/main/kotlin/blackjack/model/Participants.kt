package blackjack.model

import blackjack.model.pack.Pack

class Participants(
    val players: Set<Player>,
    val dealer: Dealer,
) {
    init {
        require(players.map { it.name }.distinct().size == players.size) { "Player 들의 이름은 중복이 허용되지 않습니다" }
    }

    fun dealing(pack: Pack) {
        players.forEach { it.deal(pack) }
        dealer.play(pack)
    }

    private fun isGameOver(): Boolean {
        return (players.any { Referee.isGameOver(it) }) || Referee.isGameOver(dealer)
    }

    fun isContinue(): Boolean {
        return !isGameOver()
    }

    fun count(): Int {
        return players.size + 1
    }
}
