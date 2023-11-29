package blackjack.domain

data class Player(override val name: Nickname, val betAmount: Amount) : Participant {

    private val _cards = mutableListOf<Card>()
    override val cards: List<Card> = _cards

    constructor(inputs: Pair<Nickname, Amount>) : this(inputs.first, inputs.second)

    override fun receiveCard(card: Card) {
        _cards.add(card)
    }

    override fun canDraw(): Boolean = calculateScore() < DRAW_CONDITION

    companion object {
        private const val DRAW_CONDITION = 21
    }
}
