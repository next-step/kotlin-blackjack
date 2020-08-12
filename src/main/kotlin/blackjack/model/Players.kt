package blackjack.model

import blackjack.model.card.CardDeck
import blackjack.model.card.Deck

class Players(
    names: List<String>
) {
    private val players = names.map(::Player)

    fun gameBatting(
        cardDeck: Deck,
        showPlayer: (Player) -> Unit
    ) =
        runPlayers { player ->
            player.gameBatting(cardDeck.popTwoCard())
            showPlayer(player)
        }

    fun processTurns(
        isHit: (Player) -> Boolean,
        showPlayer: (Player) -> Unit,
        cardDeck: CardDeck
    ) =
        runPlayers { player ->
            while (isHit(player)) {
                player.hit(cardDeck.popCard())
                showPlayer(player)
            }
        }

    override fun toString() = players.map(Player::toString).joinToString("\n")

    private fun runPlayers(action: (Player) -> Unit) {
        players.forEach(action)
    }
}
