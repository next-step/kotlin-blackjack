package blackjack.domain

const val BUST_SCORE = 21

abstract class Participant(
    val name: ParticipantName,
    cards: List<Card> = listOf(),
) {
    protected val cards: Cards = Cards(cards.toMutableList())

    val currentCards: List<DrawCard>
        get() = cards.currentCards

    fun totalCardScore(): Int = cards.calculateTotalScore()

    abstract fun isDealer(): Boolean
    abstract fun addCard(card: Card): DrawCard
    abstract fun canDraw(): Boolean
    abstract fun stopDraw()
}

@JvmInline
value class ParticipantName(
    val value: String,
)
