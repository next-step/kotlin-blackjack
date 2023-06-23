package blackjack.domain

import blackjack.view.ViewCallback

class BlackJackTable {

    private val blackJackCards: Cards = CardType.getCardDeck(CardNumber.values())

    init {
        check(blackJackCards.cards.size == blackJackCards.cards.distinct().size) {
            "카드는 중복이면 안됨"
        }
//        println(blackJackCards.cards)
    }

    private fun giveCardsToPlayer(player: Player, repeatTime: Int = DEFAULT_CARD_COUNT) {
        repeat(repeatTime) {
            player.addCard(hitCard())
        }
    }

    private fun hitCard(): Card? {
        return blackJackCards.cards.removeFirstOrNull()
    }

    fun startGame(players: Players, viewCallback: ViewCallback) {
        players.players.forEach {
            giveCardsToPlayer(it, START_CARD_COUNT)
        }
        viewCallback.showPlayerSet(players)

        players.players.forEach {
            giveMoreCard(viewCallback, it)
        }

        players.players.forEach {
            viewCallback.showGameResult(it)
        }
    }

    private fun giveMoreCard(viewCallback: ViewCallback, player: Player) {
        do {
            val isMoreCard = inputMoreCard(viewCallback, player)
            viewCallback.showPlayerCards(player)
        } while (isMoreCard)
    }

    private fun inputMoreCard(viewCallback: ViewCallback, it: Player): Boolean {
        val isMoreCard = viewCallback.isMoreCard(it)
        if (isMoreCard) {
            giveCardsToPlayer(it)
        }
        return isMoreCard
    }

    companion object {
        const val START_CARD_COUNT = 2
        private const val DEFAULT_CARD_COUNT = 1
    }
}
