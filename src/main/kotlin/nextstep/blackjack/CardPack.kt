package nextstep.blackjack

class CardPack {

    private val _cardPack: MutableList<Card> = Card.values().toMutableList().apply { shuffle() }
    val cardPack: List<Card>
        get() = _cardPack.toList()

    fun draw(): Card = _cardPack.removeFirst()
}
