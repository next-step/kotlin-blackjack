package blackjack.domain.player

class Dealer(name: String = "딜러") : BlackJackPlayer(PlayerName(name)) {

    var earnMoney = 0
        private set

    val hasGetMoreCard
        get() = cards.hasMoreCard(Players.START_CARD_COUNT)

    fun shouldGetMoreCard(): Boolean {
        return cards.hasLessScore(DEALER_SHOULD_GET_CARD_SCORE)
    }

    fun addEarnMoney(money: Int) {
        earnMoney += money
    }

    companion object {
        const val ZERO_COUNT = 0
        const val DEALER_SHOULD_GET_CARD_SCORE = 17
    }
}
