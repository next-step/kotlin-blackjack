package blackjack.domain

enum class ParticipantStatus {
    PLAYING,
    STAY,
    BURST,
    ;

    fun isPlayable(): Boolean = this == PLAYING
}
