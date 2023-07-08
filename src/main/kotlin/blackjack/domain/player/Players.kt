package blackjack.domain.player

import blackjack.domain.GameConditionNotify
import blackjack.domain.GameMoney
import blackjack.domain.card.BlackCardDeck

class Players(private val players: List<Player>, private val deck: BlackCardDeck) {

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
            player.addCard(deck.hitCard())
        }
    }

    fun giveMoreCard(gameConditionNotify: GameConditionNotify) {
        if (dealer.shouldGetMoreCard()) {
            dealer.addCard(deck.hitCard())
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
        var dealerIncome = GameMoney()
        players.forEach {
            it.matchGameScore(dealer)
            dealerIncome += it.finalIncome.minusMoney()
        }
        dealer.earnMoney = dealerIncome
    }

    companion object {
        private const val DEFAULT_CARD_COUNT = 1
        const val START_CARD_COUNT = 2
        const val MONEY_ZERO = 0
    }
}
