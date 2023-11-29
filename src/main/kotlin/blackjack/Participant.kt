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
        val card = Card.getCard()

        val cardNumber =  if (card.cardValue.value == StringValue.A) {
            StringValue.addAce(score)
        } else card.cardValue.getNumber()

        score += cardNumber
        if (score <= 21) {
            card.received()
            cards.add(card)
        } else {
            score -= cardNumber
            receive = false
        }
    }
}
