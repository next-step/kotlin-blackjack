package blackjack.model

import blackjack.dto.Card
import blackjack.dto.Deck
import blackjack.dto.GameResult
import blackjack.dto.Money
import blackjack.dto.PlayerStatus

class Dealer(name: String, bettingMoney: Money) : Player(name, bettingMoney) {

    private val cardSet = Deck.newDeck()
    private var pointer = 0
    var resultMoney = bettingMoney

    fun dealingTwoCards(): List<Card> {
        require(pointer + 2 < cardSet.size) { "카드가 부족합니다." }
        return cardSet
            .subList(pointer, pointer + 2)
            .apply { pointer += 2 }
    }

    fun dealingOneCard(): Card {
        require(pointer + 1 < cardSet.size) { "카드가 부족합니다." }
        return cardSet[pointer++]
    }

    fun initialCardDealing() {
        addCards(dealingTwoCards())
    }

    fun moreCard(): Boolean {
        var hitted = false
        if (status == PlayerStatus.HIT && getPoints() <= DEALER_HIT_POINT) {
            addCard(dealingOneCard())
            hitted = true
        }
        stay()

        return hitted
    }

    fun compareWithPlayers(players: Players) {
        players.values
            .map { it.compare(this) }

        setGameResult(
            GameResult(getPoints(), PlayerResultStatus.TIE) // 딜러는 상태가 어떻든 상관 없다
        )
    }

    override fun getPrice(): Money = resultMoney

    companion object {
        const val DEALER_NAME = "딜러"
        private const val DEALER_HIT_POINT = 16
    }
}
