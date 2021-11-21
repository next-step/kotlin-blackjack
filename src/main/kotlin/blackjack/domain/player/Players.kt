package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.strategy.assign.AssignCardStrategy
import blackjack.strategy.split.SplitStrategy

@JvmInline
value class Players private constructor(val players: List<Player>) {

    fun assignCards(deck: Deck, assignCardStrategy: AssignCardStrategy): Players =
        Players(players.map { player -> player + assignCardStrategy.assign(deck) })

    companion object {
        fun of(names: String, splitStrategy: SplitStrategy): Players =
            from(splitStrategy.split(names).map(Player::fromName))

        fun from(players: List<Player>): Players = Players(players.toList())
    }
}
