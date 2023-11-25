package blackjack.domain.model

enum class DrawState {
    HIT, STAY;

    fun isHit() = this == HIT
}
