package blackjack.domain

class DealerCards {
    val cards: Cards = Cards()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getCardsSum(): Int {
        val sumOfBasicCards = cards.getSumOfBasicCards()
        return if (!cards.hasAceCards()) {
            sumOfBasicCards
        } else {
            cards.getAceCards()
                .flatMap { card -> card.number.number.map { it + sumOfBasicCards } }
                .filter { it > Cards.GAME_LIMIT_NUMBER }
                .max()
        }
    }
}
