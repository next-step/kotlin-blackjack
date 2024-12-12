package blackjack.domain

data class Players(val value: List<Player>) {
    fun onPreparePlay(action: (Player) -> Unit): Players {
        value.forEach { action(it) }
        return this
    }

    fun onStartPlay(action: (Player) -> Unit): Players {
        value.forEach { action(it) }
        return this
    }

    fun onEndPlay(action: (Player) -> Unit): Players {
        value.forEach { action(it) }
        return this
    }
}
