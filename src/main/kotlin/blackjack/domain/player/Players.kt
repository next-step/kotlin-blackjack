package blackjack.domain.player

import blackjack.domain.GameConditionNotify
import blackjack.domain.card.CardDeck

class Players(private val players: MutableList<Player>, private val deck: CardDeck) {

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
            player.addCard(deck.getCard())
        }
    }

    fun giveMoreCard(gameConditionNotify: GameConditionNotify) {
        if (dealer.shouldGetMoreCard()) {
            dealer.addCard(deck.getCard())
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
            it.matchGameScore(dealer)
        }
    }

    companion object {
        private const val DEFAULT_CARD_COUNT = 1
        const val START_CARD_COUNT = 2
    }
}
