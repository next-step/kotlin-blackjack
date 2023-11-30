package blackjack.domain

class Cards(val cards: MutableList<Card>) {

    fun addCard(card: Card): Cards {
        cards.add(card)
        return from(cards)
    }

    fun dealCard(): Card {
        return cards.removeFirstOrNull() ?: throw IllegalStateException("카드가 모두 소진되었습니다.")
    }

    companion object {
        fun from(cardValues: List<Card>): Cards {
            return Cards(cardValues?.toMutableList() ?: mutableListOf())
        }
    }
}
