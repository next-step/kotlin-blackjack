package blackjack.domain

class Deck(val cards: Cards) {

    val count: Int = cards.count

    companion object {
        private fun initCards(): Cards {
            val cards = Cards()
            CardShape.values().map { shape -> CardNumber.values().map { number -> cards.add(Card(number, shape)) } }
            return cards
        }
    }
}
