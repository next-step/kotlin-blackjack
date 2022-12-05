package blackjack.domain

data class Deck(val cards: Cards = initCards()) {
    companion object {
        private fun initCards(): Cards {
            val cards = Cards()
            CardShape.values().map { shape ->
                CardNumber.values().map { number -> cards.add(Card(number, shape)) }
            }
            return cards
        }
    }
}
