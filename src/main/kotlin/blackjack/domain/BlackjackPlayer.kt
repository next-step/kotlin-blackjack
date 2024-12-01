package blackjack.domain

class BlackjackPlayer(
    override val name: String,
    private val _cards: MutableSet<BlackjackCard> = mutableSetOf(),
) : Player {
    override val cards: Set<BlackjackCard>
        get() = _cards

    override infix fun receive(card: BlackjackCard) {
        _cards.add(card)
    }
}
