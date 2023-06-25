package blackjack.domain

class Dealer(
    val deck: MutableList<Card>
) {

    fun getCardsByCount(count: Int): List<Card> {
        val selectedCards = deck.shuffled().take(count)
        selectedCards.forEach { card ->
            deck.remove(card)
        }
        return selectedCards
    }
}
