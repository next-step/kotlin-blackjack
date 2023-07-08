package blackjack.domain.gamer

import blackjack.domain.CardNumberCalculator
import blackjack.domain.card.Card

open class BlackJackGamer(val name: String, var money: Int) {
    private val cards = mutableListOf<Card>()
    private val cardNumberCalculator = CardNumberCalculator()

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

    open fun proceedGameRecord(gameRecordType: GameRecordType) {}
}
