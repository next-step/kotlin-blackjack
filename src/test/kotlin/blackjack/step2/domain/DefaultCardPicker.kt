package blackjack.step2.domain

class DefaultCardPicker : CardPicker {
    override fun pick(): Card {
        return Card(number = CardNumber.TWO, type = CardType.HEART)
    }

    override fun pick(count: Int): List<Card> {
        val card1 = Card(number = CardNumber.TWO, type = CardType.HEART)
        val card2 = Card(number = CardNumber.THREE, type = CardType.HEART)

        return listOf(card1, card2)
    }
}
