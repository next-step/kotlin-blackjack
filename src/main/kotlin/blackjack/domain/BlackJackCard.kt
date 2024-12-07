package blackjack.domain

data class BlackJackCard private constructor(
    val shape: BlackJackCardShape,
    val number: BlackJackCardNumber,
) {
    companion object {
        private val blackJackCards: MutableMap<Pair<BlackJackCardShape, BlackJackCardNumber>, BlackJackCard> = mutableMapOf()

        fun get(
            blackJackCardShape: BlackJackCardShape,
            blackJackCardNumber: BlackJackCardNumber,
        ): BlackJackCard {
            return blackJackCards.getOrPut(
                Pair(blackJackCardShape, blackJackCardNumber),
            ) { BlackJackCard(blackJackCardShape, blackJackCardNumber) }
        }
    }
}
