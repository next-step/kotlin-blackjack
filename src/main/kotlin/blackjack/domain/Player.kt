package blackjack.domain

class Player(name: String, bettingAmount: Int) : BlackjackParticipant(name, bettingAmount) {
    constructor(name: String) : this(name, bettingAmount = 0)

    override val canHit: Boolean = (getScore() < Score.BLACKJACK && !isBusted)
}
