package blakjack.domain

import blakjack.domain.Participant.ParticipantType.DEALER

class Dealer(
    name: String = "딜러",
    private val cardDeck: CardDeck = CardDeck.create()
) : Participant(name, DEALER) {
    var winCount = 0
        private set
    var drawCount = 0
        private set
    var loseCount = 0
        private set

    private var profit = Money.ZERO

    val isScoreToStand: Boolean
        get() = this.score >= SEVENTEEN

    fun drawOneCard(): Card {
        return cardDeck.getCardRandomly()
    }

    fun drawTwoCards(): Cards {
        return Cards(
            setOf(
                this.drawOneCard(),
                this.drawOneCard(),
            )
        )
    }

    override fun win(other: Participant) {
        super.win(other)
        winCount++
        profit += (other as Player).bettingMoney
    }

    override fun draw() {
        drawCount++
    }

    override fun lose(other: Participant) {
        loseCount++
        profit -= (other as Player).bettingMoney
    }

    override fun profit(): Money {
        return profit
    }

    companion object {
        const val SEVENTEEN = 17
    }
}
