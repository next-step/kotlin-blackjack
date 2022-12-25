package blackjack.domain.system

import blackjack.domain.player.Players

class System {

    fun checkBlackJack(players: Players): Boolean {
        return players.players.any { player ->
            player.getCards().size == BLACKJACK_CARD_COUNT && player.getScore() == BLACKJACK_SCORE
        }
    }

    companion object {
        private const val BLACKJACK_CARD_COUNT = 2
        private const val BLACKJACK_SCORE = 21
    }
}
