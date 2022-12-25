package blackjack.domain.player

import blackjack.domain.card.Deck

class Players(
    val players: List<Player>
) {

    fun startGame(gameDeck: Deck) {
        repeat(2) {
            val newCard = gameDeck.draw()
            players.forEach { player ->
                player.hit(newCard)
            }
        }
    }
}
