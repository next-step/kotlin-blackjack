package blackjack.domain.deck

data class Card(val pip: String, val suit: Suit) {

    val score = when {
        pip == "A" -> 11
        pip.toIntOrNull() == null -> 10
        else -> pip.toInt()
    }
}
