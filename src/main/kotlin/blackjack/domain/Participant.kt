package blackjack.domain

interface Participant {
    fun receiveCards(newCards: List<Card>)
    fun receiveCard(newCard: Card)
}
