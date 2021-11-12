package blackJack.domain

class PlayingCard(private val cards: Cards) {

    companion object {
        fun create(): PlayingCard {
            return PlayingCard(Cards.create())
        }
    }
}
