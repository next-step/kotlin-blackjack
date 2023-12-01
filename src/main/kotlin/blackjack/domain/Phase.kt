package blackjack.domain

sealed interface Phase {
    fun isFinish(): Boolean

    class PlayerPhase(val player: Player) : Phase {
        override fun isFinish(): Boolean = player.resolved()
    }

    class EndPhase(val players: Players) : Phase {
        override fun isFinish(): Boolean = true
    }
}
