package blackjack.domain

import blackjack.domain.player.Player
import blackjack.strategy.split.SplitStrategy

class Players private constructor(private val players: List<Player>) {
    companion object {
        fun of(names: String, splitStrategy: SplitStrategy): Players = from(splitStrategy.split(names))
        fun from(names: List<String>): Players = Players(names.map(Player.Companion::fromName))
    }
}
