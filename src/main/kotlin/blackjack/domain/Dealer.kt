package blackjack.domain

class Dealer {
    private val cardDeck = CardDeck()
    private val _dealerCards = mutableListOf<Card>()
    val dealerCards: Cards = Cards(_dealerCards)

    fun initParticipants(
        fetchNames: () -> List<String>,
        onPlayerInit: (List<String>) -> Unit,
    ): Participants {
        val names = fetchNames()
        val players = names.map { Player(it, cardDeck) }
        drawCardsForDealer()
        onPlayerInit(names)
        return Participants(dealer = this, players = players)
    }

    private fun drawCardsForDealer() {
        repeat(DEALER_CARD_COUNT) {
            _dealerCards.add(cardDeck.draw())
        }
    }

    companion object {
        private const val DEALER_CARD_COUNT = 2
    }
}