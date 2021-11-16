package blackjack.domain

import blackjack.domain.player.Player
import blackjack.strategy.assign.AssignCardStrategy
import blackjack.strategy.split.SplitStrategy

class Players private constructor(private val _players: List<Player>) {
    val players: List<Player>
        get() = _players.toList()

    fun assignCards(deck: Deck, assignCardStrategy: AssignCardStrategy): Players =
        Players(_players
            .map { player -> player.addCards(assignCardStrategy.assign(deck)) }
            .toList())

    companion object {
        fun of(names: String, splitStrategy: SplitStrategy): Players =
            from(
                splitStrategy.split(names)
                    .map(Player::fromName)
            )

        fun from(players: List<Player>): Players = Players(players.toList())
    }
}
