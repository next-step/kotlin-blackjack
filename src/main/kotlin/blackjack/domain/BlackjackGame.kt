package blackjack.domain

import blackjack.domain.player.Players

class BlackjackGame(
    private val deck: Deck,
    val players: Players,
) {
    fun init() {
        players.drawInitCards(deck)
    }

    fun isPlaying(): Boolean {
        return players.isExistWaitingPlayer()
    }
}
