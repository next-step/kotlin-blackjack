package blackjack.domain

class Player(
    override val name: String,
    override val hands: Hands = Hands(),
) : Participant(name, hands) {
    fun toStay() {
        status = PlayerStatus.STAY
    }
}
