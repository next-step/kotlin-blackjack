package blackjack.domain.card

data class Card(val number: CardNumber, val type: CardType) {

    companion object {
        fun generate(): Card {
            val number = CardNumber.getRandomNumber()
            val type = CardType.getRandomCardType()
            return Card(number, type)
        }
    }
}
