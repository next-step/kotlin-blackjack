package blackjack.domain

class Dealer(
    private val cardDeck: CardDeck
) {
    private val _dealerCards = mutableListOf<Card>()
    val dealerCards: Cards = Cards(_dealerCards)
    val cardsSum: Int get() = dealerCards.sumValues()

    init {
        repeat(DEALER_CARD_COUNT) {
            addCard(drawCard())
        }
    }

    fun initPlayers(
        fetchPlayerNames: () -> List<String>,
        onPlayerInit: (List<String>) -> Unit,
    ): Players {
        val names = fetchPlayerNames()
        val players = names.map { name ->
            Player(
                name = name,
                drawCard = { cardDeck.draw() }
            )
        }
        onPlayerInit(names)
        return Players(value = players)
    }

    fun drawOneMoreCardIfNeeded(onDrawMoreCard: () -> Unit) {
        if (dealerCards.sumValues() <= 16) {
            addCard(drawCard())
            onDrawMoreCard()
        }
    }

    fun getCardForInitialDisplay(): Card {
        require(dealerCards.value.isNotEmpty()) { "Dealer should be initialized with $DEALER_CARD_COUNT cards." }
        return dealerCards.value[0]
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