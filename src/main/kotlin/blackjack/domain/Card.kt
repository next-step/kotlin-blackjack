package blackjack.domain

class Card(val type: CardType, val number: CardNumber) {

    val isAce = CardNumber.ACE == number

    override fun toString(): String {
        return "${number.description}${type.description}"
    }
}
