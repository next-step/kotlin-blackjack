package blackjack.domain

class Cards(private val cards: HashSet<Card>) {
    fun getCard(): Card {
        val card = cards.first()
        cards.remove(card)
        return card
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getTotalScore(): Int {
        return cards.sumOf {
            it.cardNumber.score
        }
    }

    fun hasAceCard(): Boolean {
        return cards.any {
            it.cardNumber == CardNumber.CARD_ACE
        }
    }

    fun getCardsSortByScore(): List<Card> {
        return cards.sortedBy { it.cardNumber.score }
    }
}
