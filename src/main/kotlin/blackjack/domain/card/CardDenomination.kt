package blackjack.domain.card

enum class CardDenomination(
    val denomination: String,
    val order: Int,
    val getValue: (Int) -> Int,
) {
    ACE("ACE", 1, { sum ->
        if (sum + 11 > 21) 1
        else 11
    }),
    TWO("2", 2, { _ -> 2 }),
    THREE("3", 3, { _ -> 3 }),
    FOUR("4", 4, { _ -> 4 }),
    FIVE("5", 5, { _ -> 5 }),
    SIX("6", 6, { _ -> 6 }),
    SEVEN("7", 7, { _ -> 7 }),
    EIGHT("8", 8, { _ -> 8 }),
    NINE("9", 9, { _ -> 9 }),
    TEN("10", 10, { _ -> 10 }),
    JACK("J", 11, { _ -> 10 }),
    QUEEN("Q", 12, { _ -> 10 }),
    KING("K", 13, { _ -> 10 });
}
