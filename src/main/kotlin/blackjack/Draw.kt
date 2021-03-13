package blackjack

class Draw(
    val nextCard: () -> Card,
    val accepted: (name: String) -> Boolean = { true },
    val result: (Player) -> Unit = { }
)
