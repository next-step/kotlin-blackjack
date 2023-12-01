package blackjack.domain

class Player(name: String) : BlackjackParticipant(name) {

    override val canHit: Boolean = (getScore() < Score.BLACKJACK)
}
