package blackjack

object OutputView {
    fun divideCard(names: List<String>) {
        println("${names.joinToString(",")}에게 2장의 카드를 나누었습니다.")
    }

    fun printCards(player: Player, isResult: Boolean = false) {
        val cards = player.cards.joinToString { "${it.value.toText()}${it.type.toText()}" }
        print("${player.name}카드: $cards")

        if (isResult) {
            print(" - 결과: ${player.sum}")
        }
        println()
    }

    fun enterLine() = println()

    private fun CardValue.toText(): String {
        return when (this) {
            CardValue.TWO -> "2"
            CardValue.THREE -> "3"
            CardValue.FOUR -> "4"
            CardValue.FIVE -> "5"
            CardValue.SIX -> "6"
            CardValue.SEVEN -> "7"
            CardValue.EIGHT -> "8"
            CardValue.NINE -> "9"
            CardValue.TEN -> "10"
            CardValue.JACK -> "J"
            CardValue.QUEEN -> "Q"
            CardValue.KING -> "K"
            CardValue.ACE -> "A"
        }
    }

    private fun CardType.toText(): String {
        return when (this) {
            CardType.SPADE -> "스페이드"
            CardType.CLOVER -> "클로버"
            CardType.DIAMOND -> "다이아몬드"
            CardType.HEART -> "하트"
        }
    }
}
