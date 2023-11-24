package blackjack.view

enum class DrawState {
    HIT, STAY;

    fun isHit() = this == HIT
}
