package blackjack.domain

interface Participant {
    fun takeCard(card: Card)
    fun takeFirstTwoCards(card1: Card, card2: Card)
    fun cardPointSum(): Int
    fun stay()
}
