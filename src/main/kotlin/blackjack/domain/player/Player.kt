package blackjack.domain.player

data class Player(
    private val id: Int,
    val name: String
) : Participant() {

    fun isWinnerCandidate() = hand.status != HandStatus.BUST

    override fun toString(): String = "$name : ${getCards().joinToString()}"
}
