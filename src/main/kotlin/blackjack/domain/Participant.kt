package blackjack.domain

sealed interface Participant {
    val name: Nickname
    val cards: List<Card>

    fun receiveCard(card: Card)

    fun canDraw(): Boolean
}

fun Participant.getScore(): Int = this.cards.calculateScore()
