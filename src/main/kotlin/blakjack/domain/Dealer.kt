package blakjack.domain

import blakjack.domain.Participant.ParticipantType.DEALER

class Dealer(
    name: String = "딜러",
    private val cardDeck: CardDeck = CardDeck.create()
) : Participant(name, DEALER) {
    private var action: ParticipantAction = ParticipantAction.NONE
    var winCount = 0
        private set
    var loseCount = 0
        private set

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

    override fun add(card: Card) {
        super.add(card)
        action = ParticipantAction.HIT
    }

    fun stand() {
        action = ParticipantAction.STAND
    }

    fun isHit(): Boolean = (action == ParticipantAction.HIT)

    fun isStand(): Boolean = (action == ParticipantAction.STAND)

    override fun win(other: Participant) {
        super.win(other)
        winCount++
    }

    override fun lose() {
        loseCount++
    }

    companion object {
        const val SEVENTEEN = 17
    }
}
