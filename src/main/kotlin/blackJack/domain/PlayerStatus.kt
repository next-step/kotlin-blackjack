package blackJack.domain

interface PlayerStatus {
    fun isPlayer(): Boolean
    fun getAbleReceivedCard(): Boolean
    fun isBlackJackPlayer(): Boolean
    fun isBustPlayer(): Boolean
    fun getScore(): Int
    fun noReceiveCard()
    fun receiveCard(isContinue: Boolean = true, drawCard: () -> Card)
}

class PlayerStatusImpl(
    private var _cards: Cards,
    private var _decisionStatus: PlayerDecision = Hit(),
) : PlayerStatus {

    val cards: Cards
        get() = _cards

    val decisionStatus: PlayerDecision
        get() = _decisionStatus

    override fun isPlayer(): Boolean = true

    override fun getAbleReceivedCard(): Boolean = _decisionStatus.isContinue()

    override fun isBlackJackPlayer(): Boolean = _decisionStatus is BlackJack

    override fun isBustPlayer(): Boolean = _decisionStatus is Bust

    override fun getScore(): Int = _cards.sumCards(_cards.sumCards())

    override fun noReceiveCard() {
        this._decisionStatus = PlayerDecision.changeDecision(getScore(), false)
    }

    override fun receiveCard(isContinue: Boolean, drawCard: () -> Card) {
        if (isContinue) {
            this._cards += drawCard.invoke()
            this._decisionStatus = PlayerDecision.changeDecision(getScore())
        } else {
            noReceiveCard()
        }
    }

    companion object {
        fun of(): PlayerStatusImpl {
            return PlayerStatusImpl(Cards(listOf()), Hit())
        }
    }
}
