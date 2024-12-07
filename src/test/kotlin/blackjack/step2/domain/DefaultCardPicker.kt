package blackjack.step2.domain

class DefaultCardPicker : CardPicker {
    override fun pick(): Card {
        return Card(number = defaultCard.number, type = defaultCard.type)
    }

    companion object {
        val defaultCard = Card(CardNumber.TWO, CardType.HEART)
    }
}
