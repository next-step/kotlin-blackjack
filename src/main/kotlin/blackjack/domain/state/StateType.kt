package blackjack.domain.state

import blackjack.domain.Deck
import blackjack.domain.Game

enum class StateType(
    private val condition: (Deck) -> Boolean,
) {
    BLACKJACK({ deck ->
        deck.size == Game.INIT_TAKE_SIZE && deck.score() == Game.THRESHOLD
    }),
    BURST({ deck ->
        deck.score() > Game.THRESHOLD
    }),
    STAY({ deck ->
        deck.score() <= Game.THRESHOLD
    });

    companion object {
        fun from(deck: Deck): StateType = values().find { it.condition(deck) } ?: STAY
    }
}
