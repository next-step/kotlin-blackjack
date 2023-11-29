package blackjack

data class Participant (
    val name: String,
    val cards: MutableList<Card> = mutableListOf(),
) {

    var receive: Boolean = true
        private set

    var score: Int = 0
        private set

    fun isReceiveCard(answer: String) {
        receive = when (answer) {
            "y" -> true
            "n" -> false
            else -> throw IllegalArgumentException("y 또는 n을 입력해주세요.")
        }
    }

    fun addCard() {
        if (score <= 21) {
            val card = Card.getCard()
            cards.add(card)
            calculateScore(card)
        } else {
            receive = false
        }
    }

    private fun calculateScore(card: Card) {
        score += if (card.cardValue.value == StringValue.A) {
            card.cardValue.getAceNumber(score)
        } else {
            card.cardValue.getNumber()
        }
    }
}

