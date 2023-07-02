package blackjack.domain.player

import blackjack.domain.GameConditionNotify
import blackjack.domain.card.BlackCardDeck
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType

class Players(private val players: MutableList<Player>) {

    private val blackJackCardDeck: BlackCardDeck = CardType.getCardDeck(CardNumber.values())

    private val dealer = Dealer()

    fun getDealer(): Dealer {
        return dealer
    }

    init {
        getPlayers().forEach {
            giveCardsToPlayer(it, START_CARD_COUNT)
        }
    }

    fun getPlayers(): List<BlackJackPlayer> {
        return listOf(dealer) + players
    }

    private fun giveCardsToPlayer(player: BlackJackPlayer, repeatTime: Int = DEFAULT_CARD_COUNT) {
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
