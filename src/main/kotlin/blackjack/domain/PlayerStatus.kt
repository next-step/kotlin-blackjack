package blackjack.domain

enum class PlayerStatus {
    PLAYING,
    STAY,
    BURST;

    fun isPlayable(): Boolean = this == PLAYING

    fun handleBurst(score: Int): PlayerStatus =
        when {
            score > BLACKJACK_VALUE -> BURST
            else -> this
        }
}
