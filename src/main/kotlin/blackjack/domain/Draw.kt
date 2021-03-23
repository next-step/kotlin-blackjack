package blackjack.domain

class Draw(
    val nextCard: () -> Card,
    val accepted: (name: String) -> Boolean = { true },
    val result: (CardPlayer) -> Unit = { }
)
