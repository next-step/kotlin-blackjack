package blackjack.domain.player

data class Player(private val id: Int, private val name: String) {

    override fun toString(): String = "$name : "
}
