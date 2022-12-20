package blackjack.domain.card

enum class CardNumber(
    val description: String,
    val calc: (Int) -> Int,
) {
    ACE("A", { num -> num + 1 }),
    NUM_2("2", { num -> num + 2 }),
    NUM_3("3", { num -> num + 3 }),
    NUM_4("4", { num -> num + 4 }),
    NUM_5("5", { num -> num + 5 }),
    NUM_6("6", { num -> num + 6 }),
    NUM_7("7", { num -> num + 7 }),
    NUM_8("8", { num -> num + 8 }),
    NUM_9("9", { num -> num + 9 }),
    NUM_10("10", { num -> num + 10 }),
    KING("K", { num -> num + 10 }),
    QUEEN("Q", { num -> num + 10 }),
    JACK("J", { num -> num + 10 });
}
