package blackjack.model

sealed class BlackjackPlayer(val cards: Cards) {

    abstract fun canReceive(): Boolean

    protected abstract fun copy(cards: Cards): BlackjackPlayer

    fun receive(card: Card): BlackjackPlayer = if (canReceive() && !hasCard(card)) {
        copy(cards + card)
    } else {
        this
    }

    fun receiveWhile(
        next: () -> Card?,
        onReceive: (Cards) -> Unit = {}
    ): BlackjackPlayer {
        var result = this
        var card: Card? = next()
        while (card != null) {
            if (result.hasCard(card)) break
            result = result.receive(card)
            onReceive(result.cards)
            card = next()
        }
        return result
    }

    private fun hasCard(card: Card): Boolean = card in cards
}
