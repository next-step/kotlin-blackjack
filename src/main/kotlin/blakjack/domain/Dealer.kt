package blakjack.domain

import blakjack.domain.Participant.ParticipantType.DEALER

class Dealer(
    name: String = "딜러",
    private val cardDeck: CardDeck = CardDeck.create()
) : Participant(name, DEALER) {
    var winCount = 0
        private set
    var loseCount = 0
        private set

    val isOver17: Boolean
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
    }

    override fun lose() {
        loseCount++
    }

    companion object {
        const val SEVENTEEN = 17
    }
}
