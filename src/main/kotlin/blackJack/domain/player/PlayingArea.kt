package blackJack.domain.player

import blackJack.domain.card.Card
import blackJack.domain.card.Cards

interface PlayingArea {
    fun getAbleReceivedCard(): Boolean
    fun isBlackJackPlayer(): Boolean
    fun isBustPlayer(): Boolean
    fun getScore(): Int
    fun noReceiveCard()
    fun receiveCard(isContinue: Boolean = true, drawCard: () -> Card)
}

class PlayingAreaImpl(
    private var _cards: Cards,
    private var _strategy: Strategy = Hit,
) : PlayingArea {

    val cards: Cards
        get() = _cards

    val strategy: Strategy
        get() = _strategy

    override fun getAbleReceivedCard(): Boolean = _strategy.isContinue()

    override fun isBlackJackPlayer(): Boolean = _strategy is BlackJack

    override fun isBustPlayer(): Boolean = _strategy is Bust

    override fun getScore(): Int = _cards.sumCards(_cards.sumCards())

    override fun noReceiveCard() {
        this._strategy = Strategy.changeDecision(getScore(), false)
    }

    override fun receiveCard(isContinue: Boolean, drawCard: () -> Card) {
        if (isContinue) {
            this._cards += drawCard.invoke()
            this._strategy = Strategy.changeDecision(getScore())
        } else {
            noReceiveCard()
        }
    }

    companion object {
        fun of(): PlayingAreaImpl {
            return PlayingAreaImpl(Cards(listOf()), Hit)
        }
    }
}
