package blackjack.domain

class OwnCards(var cards: MutableList<Card> = buildList { repeat(Draw.FIRST_DRAW_COUNT) { add(Card()) } }.toMutableList()) {

    fun sumCardNumber() = cards.sumOf { it.cardNumber.value }

    fun getCardInfos() = cards.map { it.cardNumber.display + it.pattern.display }

    fun addCard(){
        cards.add(Card())
    }
}
