package blackjack.domain
class Player(
    name: String = "",
    cards: List<Card> = emptyList()
) : Participant(name, cards) {
    override fun canReceive(): Boolean = score().stay
}
