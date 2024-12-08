package blackjack.domain

abstract class Participant(
    val name: PlayerName,
    cards: List<Card> = listOf(),
) {
    protected val cards: Cards = Cards(cards.toMutableList())

    val currentCards: List<DrawCard>
        get() = cards.currentCards

    fun totalCardScore(): Int = cards.calculateTotalScore()

    abstract fun addCard(card: Card): DrawCard
    abstract fun canDraw(): Boolean
    abstract fun stopDraw()
}
