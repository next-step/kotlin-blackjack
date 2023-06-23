package next.step.blackjack.domain

data class Card(val face: CardFace, val symbol: CardSymbol) {
    fun desc(): String = "${face.desc}${symbol.desc}"

    companion object {
        fun of(face: CardFace, symbol: CardSymbol): Card = Card(face, symbol)
    }
}