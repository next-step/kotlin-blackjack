package step2.domain

data class Card(
    val shape: CardShape,
    val score: CardScore,
) {

    fun display(): String {
        return "${score.cardNumber}${shape.shape}"
    }
}
