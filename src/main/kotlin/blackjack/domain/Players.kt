package blackjack.domain

data class Players(val playerList: List<Player>) {
    fun onPreparePlay(action: (Player) -> Unit): Players {
        playerList.forEach { action(it) }
        return this
    }

    fun onStartPlay(action: (Player) -> Unit): Players {
        playerList.forEach { action(it) }
        return this
    }

    fun onEndPlay(action: (Player) -> Unit): Players {
        playerList.forEach { action(it) }
        return this
    }
}
