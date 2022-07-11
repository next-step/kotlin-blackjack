package game.blackjack.domain

class Player(name: String, val money: Int) : Participant(name) {

    constructor(name: String) : this(name, 0)

    fun determine(response: Boolean) = hand.setStatus(if (response) Status.HIT else Status.STAY)

    fun canReceive(): Boolean = hand.isHit()

    override fun receiveUntilHit(
        action: (name: String) -> Boolean,
        showPlayerCard: (participant: Participant) -> Unit,
        drawCard: () -> Card
    ) {
        while (canReceive()) {
            determine(action(name))
            if (canReceive()) {
                receive(drawCard())
            }
            showPlayerCard(this)
        }
    }
}
