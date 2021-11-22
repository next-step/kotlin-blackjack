package blackJack.domain.player

import blackJack.domain.card.BlackJack
import blackJack.domain.card.Bust
import blackJack.domain.card.Card
import blackJack.domain.card.Cards
import blackJack.domain.card.Hit
import blackJack.domain.card.Signal

sealed interface State {
    fun toCards(): Cards
    fun toStrategy(): Signal
    fun getAbleReceivedCard(): Boolean
    fun isBlackJackPlayer(): Boolean
    fun isBustPlayer(): Boolean
    fun getScore(): Int
    fun noReceiveCard()
    fun receiveCard(isContinue: Boolean = true, drawCard: () -> Card)
}

class StateImpl(
    private var _cards: Cards,
    private var _signal: Signal = Hit,
) : State {

    private val cards: Cards
        get() = _cards

    private val signal: Signal
        get() = _signal

    override fun toCards(): Cards = cards

    override fun toStrategy(): Signal = signal

    override fun getAbleReceivedCard(): Boolean = _signal.isContinue()

    override fun isBlackJackPlayer(): Boolean = _signal is BlackJack

    override fun isBustPlayer(): Boolean = _signal is Bust

    override fun getScore(): Int {
        return _cards.sumCards()
    }

    override fun noReceiveCard() {
        this._signal = Signal.changeDecision(getScore(), false)
    }

    override fun receiveCard(isContinue: Boolean, drawCard: () -> Card) {
        if (isContinue) {
            this._cards += drawCard.invoke()
            this._signal = Signal.changeDecision(getScore())
        } else {
            noReceiveCard()
        }
    }

    companion object {
        fun of(): State {
            return StateImpl(Cards(listOf()), Hit)
        }
    }
}
