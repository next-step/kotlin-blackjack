package blackjack.domain

data class Player(
    override val name: String,
    override val myCards: Cards = Cards()
) : Participant(name, myCards) {

    override fun toString(): String {
        return "${name}카드: $myCards"
    }
}
