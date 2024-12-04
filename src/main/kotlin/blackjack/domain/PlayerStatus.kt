package blackjack.domain

enum class PlayerStatus {
    PLAYING,
    STAY,
    BURST;

    fun isPlayable(): Boolean = this == PLAYING

    fun handleStatus(score: Int): PlayerStatus =
        when {
            score == BLACKJACK_VALUE -> STAY
            score > BLACKJACK_VALUE -> BURST
            else -> PLAYING
        }
}
