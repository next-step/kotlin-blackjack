package blackjack.domain.player

sealed interface PlayerState {
    object Playing : PlayerState
    object Done : PlayerState

    companion object {
        fun of(player: Player): PlayerState {
            return if (player.isDone()) Done else Playing
        }

        private fun Player.isDone(): Boolean = with(cards.total) { isAboveTwentyOne || isBlackjack }
    }
}
