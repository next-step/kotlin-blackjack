package blackjack.view

class ResultView {
    fun renderPlayerInitOutput(names: String) = render(names + INIT_CARDS)

    fun renderPlayerCardsOutput(
        name: String,
        cards: String,
    ) = render("${name}카드 : $cards")

    fun renderPlayerCardsResultOutput(
        name: String,
        cards: String,
        sum: Int,
    ) = render("${name}카드 : $cards - 결과 : $sum")

    private fun render(message: String) {
        println(message)
    }

    private companion object {
        const val INIT_CARDS = "에게 2장의 나누었습니다."
    }
}
