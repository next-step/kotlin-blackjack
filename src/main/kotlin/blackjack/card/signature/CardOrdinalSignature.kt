package blackjack.card.signature

enum class CardOrdinalSignature(val code: String) {
    ACE("A"),
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
    ;

    companion object {
        fun pickRandomOne() = values().toMutableList().shuffled().first()
    }
}
