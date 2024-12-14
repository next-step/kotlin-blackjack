package blackjack.domain

class Dealer {
    private val cardDeck = CardDeck()

    private val _dealerCards = mutableListOf<Card>()
    val dealerCards: Cards = Cards(_dealerCards)

    init {
        repeat(DEALER_CARD_COUNT) {
            addCard(drawCard())
        }
    }

    fun initParticipants(
        fetchPlayerNames: () -> List<String>,
        onPlayerInit: (List<String>) -> Unit,
    ): Participants {
        val names = fetchPlayerNames()
        val players = names.map { Player(it, cardDeck) }
        onPlayerInit(names)
        return Participants(dealer = this, players = players)
    }

    fun play(onDrawMoreCard: () -> Unit) {
        if (dealerCards.sumValues() <= 16) {
            addCard(drawCard())
            onDrawMoreCard()
        }
    }

    private fun addCard(card: Card) {
        _dealerCards.add(card)
    }

    private fun drawCard(): Card {
        return cardDeck.draw()
    }

    companion object {
        private const val DEALER_CARD_COUNT = 2
    }
}