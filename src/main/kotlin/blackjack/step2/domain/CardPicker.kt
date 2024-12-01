package blackjack.step2.domain

interface CardPicker {
    fun pick(): Card

    fun pick(count: Int): List<Card>
}
