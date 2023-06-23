package blackjack.domain.dsl

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.Players

@BuilderMarker
class GameBuilder : Builder<Game> {
    private var players: Players? = null
    private var deck: Deck? = null
    private var dealer: Dealer? = null

    fun deck(block: DeckBuilder.() -> Unit) = DeckBuilder().apply(block).build().let {
        deck = it
    }

    fun players(block: GamersBuilder.() -> Unit) = GamersBuilder().apply(block).build().let {
        players = it
    }

    fun dealer() {
        dealer = Dealer()
    }

    override fun build(): Game = Game(
        players = players ?: throw IllegalArgumentException(),
        deck = deck ?: Deck(),
        dealer = dealer ?: Dealer()
    )
}

fun buildGame(block: GameBuilder.() -> Unit): Game = GameBuilder().apply(block).build()
