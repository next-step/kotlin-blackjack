package blackjack.domain

import java.math.BigDecimal

class Dealer(
    private val drawCard: () -> Card,
) : Participant(drawCard = drawCard) {
    fun initPlayers(
        fetchPlayerNames: () -> List<String>,
        getBettingAmount: (String) -> BigDecimal,
        onPlayerInit: (List<String>) -> Unit,
    ): Players {
        val names = fetchPlayerNames()
        val players = names.map { name ->
            val betAmount = getBettingAmount(name)
            val player = Player(
                name = name,
                betAmount = betAmount,
                drawCard = drawCard,
            )
            player
        }
        onPlayerInit(names)
        return Players(value = players)
    }

    fun drawOneMoreCardIfNeeded(onDrawCard: () -> Unit) {
        addCardIfAvailable(requireCard = drawCard, onDrawCard = onDrawCard)
    }

    override fun isAddCardEnabled(): Boolean {
        return cardsSum <= DEALER_DRAW_ONE_MORE_CARD_THRESHOLD
    }

    fun getCardForInitialDisplay(): Card {
        require(cards.value.isNotEmpty()) { "Dealer should be initialized with $DEALER_CARD_COUNT cards." }
        return cards.value[0]
    }

    companion object {
        private const val DEALER_CARD_COUNT = 2
        private const val DEALER_DRAW_ONE_MORE_CARD_THRESHOLD = 16
    }
}
