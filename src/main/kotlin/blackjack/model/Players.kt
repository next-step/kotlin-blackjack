package blackjack.model

import blackjack.model.card.Cards
import blackjack.model.player.GamePlayer
import blackjack.model.player.Player

class Players(
    names: List<String>
) {
    private val players: MutableList<Player> = names.map { GamePlayer(it) }.toMutableList()

    fun addPlayer(player: Player) {
        players.add(player)
    }

    fun gameBatting(popTwoCard: () -> Cards) =
        players.forEach { it.gameBatting(popTwoCard()) }

    fun runTurns(playerAction: (Player) -> Unit) =
        players.forEach { player ->
            playerAction(player)
            player.done()
        }

    fun toStringList() = players.map(Player::toString)

    override fun toString() = players.joinToString("\n", transform = Player::toString)
}
