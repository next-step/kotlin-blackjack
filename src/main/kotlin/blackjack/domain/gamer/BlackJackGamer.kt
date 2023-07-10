package blackjack.domain.gamer

import blackjack.domain.CardNumberCalculator
import blackjack.domain.card.Card

open class BlackJackGamer(val name: String, money: Int = initMoney) {
    var money = money
        private set
    private val cards = mutableListOf<Card>()
    private val cardNumberCalculator = CardNumberCalculator()

    fun takeMoney(addMoney: Int) {
        money += addMoney
    }

    fun takeMoneyOut(takenMoney: Int) {
        money -= takenMoney
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun addCards(drawCards: List<Card>) {
        cards.addAll(drawCards)
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }

    fun calculateSumOfCardNumbers(): Int {
        return cardNumberCalculator.calculateSumOfCardNumbers(cards)
    }

    open fun getGamerType(): GamerType {
        return GamerType.PLAYER
    }

    companion object {
        const val initMoney = 0
    }
}
