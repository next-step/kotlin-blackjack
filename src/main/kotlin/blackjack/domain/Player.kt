package blackjack.domain

data class Player(override val name: Nickname) : Participant {

    private val _cards = mutableListOf<Card>()
    override val cards: List<Card> = _cards

    override fun receiveCard(card: Card) {
        _cards.add(card)
    }
}
