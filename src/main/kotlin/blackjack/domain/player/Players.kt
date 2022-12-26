package blackjack.domain.player

import blackjack.domain.card.Deck

class Players(
    val players: List<Player>
) {

    fun startGame(gameDeck: Deck) {
        repeat(2) {
            players.forEach { player ->
                val newCard = gameDeck.draw()
                player.hit(newCard)
            }
        }
    }

    fun checkBlackJack() {
        val blackJackExist = players.any { player -> player.isBlackJack() }
        if (blackJackExist) {
            players.forEach { player -> player.stay() }
        }
    }

    fun isAllFinished(): Boolean {
        return players.all { player -> player.isFinished() }
    }

    fun getUnfinishedPlayer(): Player {
        return players.first { player -> player.isFinished().not() }
    }
}
