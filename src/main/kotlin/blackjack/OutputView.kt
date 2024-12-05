package blackjack

object OutputView {
    private const val SPADE_STR = "스페이드"
    private const val HEART_STR = "하트"
    private const val DIAMOND_STR = "다이아몬드"
    private const val CLOVER_STR = "클로버"

    fun printDefaultPlayerCards(players: List<Player>) {
        println("${players.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
        players.forEach { printPlayerCards(it) }
    }

    fun printPlayerCards(player: Player) {
        println(
            "${player.name}카드: ${
                player.getCards().joinToString { it.number.symbol + convertMarkToString(it.mark) }
            }"
        )
    }

    fun printResult(players: List<Player>) {
        players.forEach { "${printPlayerCards(it)} - 결과: ${it.getCardsMaxSum()}" }
    }

    private fun convertMarkToString(mark: CardMark) = when (mark) {
        CardMark.SPADE -> SPADE_STR
        CardMark.HEART -> HEART_STR
        CardMark.DIAMOND -> DIAMOND_STR
        CardMark.CLOVER -> CLOVER_STR
    }
}
