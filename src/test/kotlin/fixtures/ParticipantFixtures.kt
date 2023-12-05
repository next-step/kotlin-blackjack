package fixtures

import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit

private const val NAME = "player"
private val HIT_STATE = Hit(createUnderBlackjackCards())
private val BUST_STATE = Bust(createBustCards())
private val BLACKJACK_STATE = Blackjack(createBlackjackCards())

fun createHitPlayer(
    name: String = NAME,
): Player {
    return Player(name, HIT_STATE)
}

fun createBustPlayer(
    name: String = NAME
): Player {
    return Player(name, BUST_STATE)
}

fun createBlackjackPlayer(
    name: String = NAME
): Player {
    return Player(name, BLACKJACK_STATE)
}

fun createPlayers(vararg players: Player): Players {
    return Players(
        players.toList()
    )
}
