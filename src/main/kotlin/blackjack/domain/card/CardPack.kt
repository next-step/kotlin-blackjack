package blackjack.domain.card

class CardPack(cardFactory: CardFactory = DefaultCardFactory()) {
    private val cards = cardFactory.createCards().toMutableList()

    fun poll(): Card {

        check(cards.isNotEmpty()) { "모든 카드가 사용되었습니다." }

        return cards.removeAt(0)
    }
}
