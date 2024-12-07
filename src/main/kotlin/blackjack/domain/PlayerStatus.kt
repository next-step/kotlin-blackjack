package blackjack.domain

enum class PlayerStatus {
    PLAYING,
    STAY,
    BURST,
    ;

    fun isPlayable(): Boolean = this == PLAYING
}
