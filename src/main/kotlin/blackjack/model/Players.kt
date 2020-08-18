package blackjack.model

import blackjack.model.card.Cards
import blackjack.model.player.GamePlayer
import blackjack.model.player.Player
import blackjack.model.status.GameResult

class Players(
    names: List<String> = emptyList(),
    private val players: MutableList<Player> = names.map { GamePlayer(it) }.toMutableList()
) {

    fun gameBatting(popTwoCard: () -> Cards) =
        players.forEach { it.gameBatting(popTwoCard()) }

    fun runTurns(playerAction: (Player) -> Unit) =
        players.forEach { player ->
            playerAction(player)
            player.done()
        }

    fun getGameResults(dealer: Player) = GameResult(players, dealer)

    fun toStringList() = players.map(Player::toString)

    override fun toString() = players.joinToString("\n", transform = Player::toString)
}
