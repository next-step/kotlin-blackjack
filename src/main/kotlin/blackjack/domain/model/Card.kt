package blackjack.domain.model

class Card private constructor(
    val number: CardNumber,
    val shape: CardShape,
    val scores: List<Score>
) {
    companion object {
        private val CARD_ACE_SCORES = listOf(Score(1), Score(11))
        private val CARD_KQJ_SCORES = listOf(Score(10))

        fun of(number: CardNumber, shape: CardShape): Card {
            if (number.isAce()) {
                return Card(number, shape, CARD_ACE_SCORES)
            }

            if (number.isOneOfKQJ()) {
                return Card(number, shape, CARD_KQJ_SCORES)
            }

            return Card(number, shape, listOf(Score(number.number)))
        }
    }
}
