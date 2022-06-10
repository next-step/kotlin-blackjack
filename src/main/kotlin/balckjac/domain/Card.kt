package balckjac.domain

data class Card(
    val suit: Suit,
    val denomination: Denomination
) {
    val fullName = "${suit.cardName}${denomination.cardName}"
}

fun List<Card>.asText() = this.joinToString { it.fullName }
