package blackjack.view

object OutputView {
    fun printPlayerCards(playerResponse: List<PlayerResponse>) {
        val names = playerResponse.map { it.name }
        println("${names.joinToString { it }}에게 2장의 카드를 나누었습니다.")

        playerResponse.forEach { printPlayerCard(it) }
    }

    fun printPlayerCard(playerResponse: PlayerResponse) {
        val cards = convertToCardForView(playerResponse)
        println("${playerResponse.name}카드: ${cards.joinToString { it }}")
    }

    fun printPlayerResult(playerResponse: PlayerResponse, sum: Int) {
        val cards = convertToCardForView(playerResponse)
        println("${playerResponse.name}카드: ${cards.joinToString { it }} - 결과: $sum")
    }

    private fun convertToCardForView(playerResponse: PlayerResponse) =
        playerResponse.cards.map { Value.valueOf(it.name).viewName + Pattern.valueOf(it.pattern).viewName }
}

enum class Pattern(val viewName: String) {
    SPADE("스페이드"),
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드")
}

enum class Value(val viewName: String) {
    ACE("A"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10");
}
