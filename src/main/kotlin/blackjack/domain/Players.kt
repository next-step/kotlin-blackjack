package blackjack.domain

import blackjack.domain.player.Player
import blackjack.strategy.assign.AssignCardStrategy
import blackjack.strategy.split.SplitStrategy

class Players private constructor(val players: List<Player>) {

    fun assignCards(deck: Deck, assignCardStrategy: AssignCardStrategy): Players =
        Players(
            players
                .map { player -> player.addPlayingCards(assignCardStrategy.assign(deck)) }
                .toList()
        )

    companion object {
        fun of(names: String, splitStrategy: SplitStrategy): Players =
            from(
                splitStrategy.split(names)
                    .map(Player::fromName)
            )

        fun from(players: List<Player>): Players = Players(players.toList())
    }
}
