package blackjack.model

data class Player(val name: Name, val cards: Cards) {

    fun receive(card: Card): Player = if (hasCard(card)) {
        copy(cards = cards)
    } else {
        copy(cards = cards + card)
    }

    fun receiveWhile(limit: Int, onDraw: () -> Card?): Player {
        var result = copy()
        repeat(limit) {
            val card = onDraw()
            if (card == null || hasCard(card)) return result
            result = result.receive(card)
        }
        return result
    }

    private fun hasCard(card: Card): Boolean = card in cards
}
