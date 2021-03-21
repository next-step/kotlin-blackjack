package blackjack.domain

abstract class GameParticipants(
    val name: String
) {
    val cards: Cards = Cards(arrayListOf())

    init {
        repeat(PLAYER_INIT_CARD) {
            drawCard()
        }
    }

    abstract fun drawCard()
    abstract fun calculateMyCards(): Int

    companion object {
        const val PLAYER_INIT_CARD = 2
    }
}
