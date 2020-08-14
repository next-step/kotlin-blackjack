package blackjack.domain

class Challenger(
    name: String,
    cards: Cards = Cards.empty(),
    state: State = getPlayerStateFrom(cards)
) : Player(name, cards, state) {

    override fun getStateFrom(cards: Cards): State {
        return getPlayerStateFrom(cards)
    }

    override fun copy(name: String, cards: Cards, state: State): Player {
        return Challenger(name, cards, state)
    }
}
