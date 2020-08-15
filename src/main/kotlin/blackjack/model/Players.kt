package blackjack.model

import blackjack.model.card.Cards

class Players(
    names: List<String>
) {
    private val players = names.map { Player(it) }

    fun gameBatting(popTwoCard: () -> Cards) =
        players.forEach { it.gameBatting(popTwoCard()) }

    fun runTurns(playerAction: (Player) -> Unit) =
        players.forEach { playerAction(it) }

    override fun toString() = players.joinToString("\n", transform = Player::toString)
}
