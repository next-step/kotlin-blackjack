package blackjack.domain.player

data class Player(
    private val id: Int,
    val name: String
) : Participant() {

    override fun toString(): String = "$name : ${super.toString()}"
}
