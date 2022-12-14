package nextstep.blackjack

class CardPack {

    private val cardPack: MutableList<Card> = Card.values().toMutableList().apply { shuffle() }

    fun draw(): Card = cardPack.removeFirst()
}
