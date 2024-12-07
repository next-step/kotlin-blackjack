package blackjack.step2.domain

class RandomCardPicker : CardPicker {
    override fun pick(): Card {
        return Card(CardNumber.random(), CardType.random())
    }
}
