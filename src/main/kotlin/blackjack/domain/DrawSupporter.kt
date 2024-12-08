package blackjack.domain

class DrawSupporter(
    private val deck: Deck = Deck.randomCardDeck(),
    private var drawOrder: Int = 0
) {
    val currentDrawOrder
        get() = drawOrder

    fun drawCard(): Card = deck.draw()

    fun incrementDrawOrder(targetOrder: Int, playerSize: Int) {
        this.drawOrder = (targetOrder + 1) % playerSize
    }
}
