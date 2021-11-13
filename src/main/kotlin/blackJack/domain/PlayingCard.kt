package blackJack.domain

class PlayingCard(cards: Cards) {

    private var _cards: Cards = cards

    fun drawCard(): Card {
        val randomCard = _cards.drawRandomCard()
        _cards -= randomCard
        return randomCard
    }

    companion object {
        fun create(): PlayingCard {
            return PlayingCard(Cards.create())
        }
    }
}
