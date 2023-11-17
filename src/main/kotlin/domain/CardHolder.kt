package domain

interface CardHolder {
    fun receiveCard(card: Card)
    fun showHand(): List<Card>
}
