package blackjack

class CardNumber(val value: Int = (1..13).random()) {

    fun toName(): String = when (value) {
        1 -> "Ace"
        11 -> "Jack"
        12 -> "Queen"
        13 -> "Jack"
        else -> value.toString()
    }
}
