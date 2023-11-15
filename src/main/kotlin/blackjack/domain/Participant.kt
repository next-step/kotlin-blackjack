package blackjack.domain

interface Participant {
    val name: Nickname
    val cards: List<Card>

    fun receiveCard(card: Card)
}
