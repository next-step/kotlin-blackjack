package blackjack.domain

data class Player(val name: String) {
    private var cards = mutableListOf<Card>()

    fun drawCard(newCard: Card) {
        cards.add(newCard)
    }

    fun calculateCard(): Int {
        return cards.sumOf { card -> card.getCardNumber(card.number) }
    }

    fun getAllCards(): List<Card> {
        return cards.toList()
    }

    fun isDone(): Boolean = calculateCard() > 21
}