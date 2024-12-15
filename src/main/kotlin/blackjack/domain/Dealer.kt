package blackjack.domain

class Dealer(
    private val drawCard: () -> Card,
) : Participant(drawCard = drawCard) {
    fun initPlayers(
        fetchPlayerNames: () -> List<String>,
        onPlayerInit: (List<String>) -> Unit,
    ): Players {
        val names = fetchPlayerNames()
        val players = names.map { name -> Player(name = name, drawCard = drawCard) }
        onPlayerInit(names)
        return Players(value = players)
    }

    fun drawOneMoreCardIfNeeded(onDrawCard: () -> Unit) {
        addCardIfAvailable(requireCard = { drawCard() }, onDrawCard = onDrawCard)
    }

    override fun isAddCardEnabled(): Boolean {
        return cardsSum <= 16
    }

    fun getCardForInitialDisplay(): Card {
        require(cards.value.isNotEmpty()) { "Dealer should be initialized with $DEALER_CARD_COUNT cards." }
        return cards.value[0]
    }

    companion object {
        private const val DEALER_CARD_COUNT = 2
    }
}
