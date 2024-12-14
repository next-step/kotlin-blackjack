package blackjack.domain

data class Players(val value: List<Player>) {
    fun onEachPreparePlay(action: (Player) -> Unit): Players {
        value.forEach { action(it) }
        return this
    }

    fun onEachStartPlay(action: (Player) -> Unit): Players {
        value.forEach { action(it) }
        return this
    }

    fun onEach(action: (Player) -> Unit): Players {
        value.forEach { action(it) }
        return this
    }
}
