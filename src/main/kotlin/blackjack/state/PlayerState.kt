package blackjack.state

sealed interface PlayerState {
    fun isPlaying(): Boolean
    fun isFinished(): Boolean
    fun isHit(): Boolean
    fun isStay(): Boolean
    fun isBust(): Boolean
    fun isBlackjack(): Boolean
    fun isStart(): Boolean
}
