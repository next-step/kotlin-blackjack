package blackjack.domain

interface Participant {
    fun takeCard(card: Card)
    fun cardPointSum(): Int
}
