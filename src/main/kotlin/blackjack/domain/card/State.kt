package blackjack.domain.card

enum class State(val isFinished: Boolean) {

    BEGIN(false),
    HIT(false),
    STAY(true),
    BLACKJACK(true),
    BUST(true);
}
