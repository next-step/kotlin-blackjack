package domain.card

fun MutableList<BlackjackCard>.pop(): BlackjackCard = this.removeAt(0)

class Deck(private val cards: MutableList<BlackjackCard>) {
    val cardCount
        get() = cards.size

    fun issueCard(): BlackjackCard = cards.pop()
}
