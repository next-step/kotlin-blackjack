package blackjack.util

import blackjack.domain.Player

fun List<Player>.onPreparePlay(action: (Player) -> Unit): List<Player> {
    forEach { it.onPreparePlay(action) }
    return this
}

fun List<Player>.onStartPlay(action: (Player) -> Unit): List<Player> {
    forEach { it.onStartPlay(action) }
    return this
}

fun List<Player>.onEndPlay(action: (Player) -> Unit): List<Player> {
    forEach { it.onEndPlay(action) }
    return this
}

fun Player.onPreparePlay(action: (Player) -> Unit): Player {
    action(this)
    return this
}

fun Player.onStartPlay(action: (Player) -> Unit): Player {
    action(this)
    return this
}

fun Player.onEndPlay(action: (Player) -> Unit): Player {
    action(this)
    return this
}
