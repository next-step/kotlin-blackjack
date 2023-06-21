package blackjack.domain.dsl

import blackjack.domain.Gamer
import blackjack.domain.Player
import blackjack.domain.Players

@BuilderMarker
class GamersBuilder : Builder<Players> {
    private val gamers: MutableList<Player> = mutableListOf()

    fun name(value: String) {
        gamers.add(Gamer(name = value))
    }

    override fun build(): Players = Players(values = gamers.toList())
}
