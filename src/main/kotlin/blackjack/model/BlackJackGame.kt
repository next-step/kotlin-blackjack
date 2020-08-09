package blackjack.model

import blackjack.model.card.CardDeck

class BlackJackGame(
    private val players: List<Player>,
    private val cardDeck: CardDeck
) {
    fun gameRun(
        isHit: (String) -> Boolean,
        showPlayer: (Player) -> Unit
    ): List<Player> {
        players.forEach {
            it.gameBatting(cardDeck)
            showPlayer(it)
        }

        players.forEach {
            questionHit(it, isHit, showPlayer)
        }

        return players
    }

    private fun questionHit(player: Player, isHit: (String) -> Boolean, showPlayer: (Player) -> Unit) {
        while (isHit(player.name)) {
            player.hit(cardDeck)
            showPlayer(player)
        }
    }
}
