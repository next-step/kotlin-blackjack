package blackject.model

import blackject.model.card.Card
import blackject.model.card.Cards
import blackject.model.card.CardsDeck

/**
 * 블랙잭 게임에 참가하는 플레이어
 * */
open class Player(
    val name: String,
    val cards: Cards = Cards(),
    private var _result: ResultType? = null,
) {
    open fun isDealer(): Boolean = false

    open fun getScore(): Int {
        return cards.getResultNumber()
    }

    open fun canTakeMoreCard(): Boolean {
        return Cards.BLACK_JACK_SUM > cards.getResultNumber()
    }

    open fun giveCard(newCards: List<Card>) {
        cards.addCard(newCards)
    }

    fun giveCards(cardCount: Int) {
        giveCard(CardsDeck.takeCard(cardCount))
    }

    fun isBlackJack(): Boolean = cards.isBlackjack(getScore())
    fun isBust(): Boolean = getScore() > Cards.BLACK_JACK_SUM

    fun hasPlusProfit(): Boolean = EarningRate.isPlusProfit(result.earningRate)

    val result: ResultType
        get() = _result ?: ResultType.Lose

    fun changeResultType(result: ResultType) {
        this._result = result
    }

    open fun calculateGameResult(winScore: Int? = null, isDealerBust: Boolean, isDealerBlackJack: Boolean) {}
}
