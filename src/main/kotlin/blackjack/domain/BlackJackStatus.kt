package blackjack.domain

enum class BlackJackStatus(val isDrawable: Boolean) {
    INIT(true),
    INIT_BLACK_JACK(false),
    BLACK_JACK(false),
    BUST(false),
    HIT(true),
    STAY(false)
}
