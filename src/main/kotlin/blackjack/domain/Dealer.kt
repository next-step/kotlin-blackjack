package blackjack.domain

class Dealer(private val deck: Deck) : Participant {

    private val _cards = mutableListOf<Card>()
    override val cards: List<Card> = _cards
    override val name: Nickname = Nickname("딜러")

    init {
        deck.shuffle()
    }

    override fun receiveCard(card: Card) {
        _cards.add(card)
    }

    fun dealCard(): Card {
        if (deck.isEmpty()) {
            deck.reset()
            deck.shuffle()
        }

        return deck.draw()
    }

    override fun canDraw(): Boolean = getScore() <= DRAW_CONDITION

    companion object {
        private const val DRAW_CONDITION = 16
    }
}
