package blackjack.domain.player

import blackjack.domain.GameMoney

class Dealer(name: String = "딜러") : BlackJackPlayer(PlayerName(name)) {

    var earnMoney: GameMoney = GameMoney()

    val hasGetMoreCard
        get() = cards.hasMoreCard(Players.START_CARD_COUNT)

    fun shouldGetMoreCard(): Boolean {
        return cards.hasLessScore(DEALER_SHOULD_GET_CARD_SCORE)
    }

    companion object {
        const val DEALER_SHOULD_GET_CARD_SCORE = 17
    }
}
