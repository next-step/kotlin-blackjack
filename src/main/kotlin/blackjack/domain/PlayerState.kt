package blackjack.domain

enum class PlayerState(
    val canDraw: Boolean,
) {
    HIT(true),
    STAND(false),
    BUST(false),
    BLACK_JACK(false)
}
