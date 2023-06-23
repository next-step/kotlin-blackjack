package next.step.blackjack.domain

data class Card(val face: CardFace, val symbol: CardSymbol) {
    fun desc() = "${face.desc}${symbol.desc}"
}