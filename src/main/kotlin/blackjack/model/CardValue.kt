package blackjack.model

enum class CardValue(val value: String) {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    J("J"),
    Q("Q"),
    K("K"),
    A("A");

    companion object {
        const val ACE_HIGH_SCORE = 11
        const val JQA_SCORE = 10
    }
}
