package blackjack.domain

interface Participant {
    val isBlackjack: Boolean//TODO : delete
    fun takeCard(card: Card)
    fun takeFirstTwoCards(card1: Card, card2: Card)//TODO : parameter를 cards로 변경
    fun cardPointSum(): Int
}
