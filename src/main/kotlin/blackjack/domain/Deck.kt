package blackjack.domain

object Deck {
    private val cards: MutableSet<Card> = hashSetOf()
    init {
        initializeCards()
    }

    fun pick(): Card {
        if(isEmpty()) throw IllegalStateException("덱에 카드가 더이상 존재하지 않습니다.")
        val card: Card = cards.random()
        cards.remove(card)
        return card
    }

    private fun isEmpty(): Boolean {
        return cards.isEmpty()
    }

    private fun initializeCards() {
        initializeCardsByCardSuit()

    }

    private fun initializeCardsByCardSuit(){
        CardSuit.values().forEach {
            initializeCardsByCardSpell(it)
        }
    }

    private fun initializeCardsByCardSpell(cardSuit: CardSuit) {
        CardSpell.values().forEach {
            this.cards.add(Card.of(cardSuit, it))
        }
    }
}
