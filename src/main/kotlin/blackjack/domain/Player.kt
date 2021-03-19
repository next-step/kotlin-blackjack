package blackjack.domain

class Player(
    val name: String
) {
    val cards = Cards(arrayListOf())

    init {
        repeat(PLAYER_INIT_CARD) {
            drawCard()
        }
    }

    fun drawCard() {
        cards.drawCard()
    }

    fun calculateMyCards(): Int {
        return cards.calculateMyCards()
    }

    companion object {
        const val PLAYER_INIT_CARD = 2
    }
}
