package blackjack

class Card(
    number: String
) {
    val isAce = number == "A"
    val value = when (number) {
        "A" -> 1
        "K", "J", "Q" -> 10
        else -> number.toInt()
    }
}
