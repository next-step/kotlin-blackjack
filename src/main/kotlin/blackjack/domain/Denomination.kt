package blackjack.domain

enum class Denomination(
    val description: String,
    val calc: (Int) -> Int,
) {
    ACE_1("A", { num -> num + 1 }),
    ACE_10("A", { num -> num + 10 }),
    TWO("2", { num -> num + 2 }),
    THREE("3", { num -> num + 3 }),
    FOUR("4", { num -> num + 4 }),
    FIVE("5", { num -> num + 5 }),
    SIX("6", { num -> num + 6 }),
    SEVEN("7", { num -> num + 7 }),
    EIGHT("8", { num -> num + 8 }),
    NINE("9", { num -> num + 9 }),
    TEN("10", { num -> num + 10 }),
    KING("K", { num -> num + 10 }),
    QUEEN("Q", { num -> num + 10 }),
    JACK("J", { num -> num + 10 });
}
