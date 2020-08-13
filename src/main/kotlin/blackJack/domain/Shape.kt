package blackJack.domain

enum class Shape(private val shape: String) {
    SPADE("♠"),
    CLOVER("♣"),
    HEART("♥"),
    DIA("♦");

    fun makeCard(number: Int): Card {
        return when (number) {
            ACE -> (Card("${shape}A", number))

            in NUMBER_TWO..NUMBER_TEN -> (Card(shape + "$number", number))

            JACK -> (Card("${shape}J", NUMBER_TEN))

            QUEEN -> (Card("${shape}Q", NUMBER_TEN))

            KING -> (Card("${shape}K", NUMBER_TEN))

            else -> throw IllegalArgumentException("해당 번호의 카드는 존재하지 않습니다.")
        }
    }

    companion object {
        private const val ACE = 1
        private const val NUMBER_TWO = 2
        private const val NUMBER_TEN = 10
        private const val JACK = 11
        private const val QUEEN = 12
        private const val KING = 13
    }
}
