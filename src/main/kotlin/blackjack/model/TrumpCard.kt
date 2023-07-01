package blackjack.model

data class TrumpCard(val shape: TrumpCardShape, val number: TrumpCardNumber) {

    val numberScore: Int
        get() = number.score

    companion object {
        val ALL: Collection<TrumpCard> = TrumpCardShape.values()
            .flatMap { shape ->
                TrumpCardNumber.values()
                    .map { number -> TrumpCard(shape, number) }
            }
    }
}
