package blackjack.domain

class Players(private val players: MutableList<Player>) {

    private val blackJackCardDeck: BlackCardDeck = CardType.getCardDeck(CardNumber.values())

    private val dealer = Dealer()

    init {
        getPlayers().forEach {
            giveCardsToPlayer(it, START_CARD_COUNT)
        }
    }

    fun getPlayers(): List<Player> {
        return listOf(dealer) + players
    }

    private fun giveCardsToPlayer(player: Player, repeatTime: Int = DEFAULT_CARD_COUNT) {
        repeat(repeatTime) {
            player.addCard(blackJackCardDeck.hitCard())
        }
    }

    fun giveMoreCard(gameConditionNotify: GameConditionNotify) {
        if (dealer.shouldGetMoreCard()) {
            dealer.addCard(blackJackCardDeck.hitCard())
        }

        players.forEach {
            giveMoreCardToPlayer(gameConditionNotify, it)
        }
    }

    private fun giveMoreCardToPlayer(gameConditionNotify: GameConditionNotify, player: Player) {
        do {
            val isMoreCard = requestGiveMoreCard(gameConditionNotify, player)
            gameConditionNotify.giveCardToPlayerDone(player)
        } while (isMoreCard)
    }

    private fun requestGiveMoreCard(gameConditionNotify: GameConditionNotify, player: Player): Boolean {
        val isMoreCard = gameConditionNotify.isNeedMoreCard(player)
        if (isMoreCard) {
            giveCardsToPlayer(player)
        }
        return isMoreCard
    }

    fun judgeGameResult() {
        players.forEach {
            dealer.match(it)
        }
    }

    companion object {
        private const val DEFAULT_CARD_COUNT = 1
        const val START_CARD_COUNT = 2
    }
}
