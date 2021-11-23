package blackjack.domain.player

import blackjack.domain.player.name.Name
import blackjack.strategy.split.SplitStrategy

@JvmInline
value class Players(private val players: List<Player>) {

    companion object {
        fun of(name: String, splitStrategy: SplitStrategy): Players =
            splitStrategy.split(name)
                .map { GamePlayer(Name(it)) }
                .let { from(it) }

        fun from(players: List<Player>): Players = Players(players.toList())
    }
}
