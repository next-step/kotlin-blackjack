package blackjack.step2.domain

class RandomCardPicker : CardPicker {
    override fun pick(): Card {
        val cardNumber = CardNumber.entries.shuffled().first()
        val cardType = CardType.entries.shuffled().first()

        return Card(cardNumber, cardType)
    }

    override fun pick(count: Int): List<Card> {
        require(count in MIN_CARD_COUNT..MAX_CARD_COUNT) { "카드는 1장 이상 52장 이하만 가능합니다." }
        val cardNumber = CardNumber.entries.shuffled().take(count)
        val cardType = CardType.entries.shuffled().take(count)

        return cardNumber.zip(cardType).map { (number, type) -> Card(number, type) }
    }

    companion object {
        const val MIN_CARD_COUNT = 1
        const val MAX_CARD_COUNT = 52
    }
}
