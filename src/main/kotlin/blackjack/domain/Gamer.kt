package blackjack.domain

data class Gamer(
    val name: String,
    val isFinish: Boolean = false,
    val cards: Cards = Cards()
) {

    override fun toString(): String {
        return "${name}카드 : $cards"
    }
}
