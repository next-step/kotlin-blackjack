package blackjack.domain

import blackjack.domain.Game.Companion.INITIAL_CARD_COUNT

data class GamePlayer(
    override val playerInfo: PlayerInfo,
    override val cards: Cards = Cards.empty(),
) : Player {

    constructor(name: String) : this(PlayerInfo(Name(name)))

    override fun initialCard(deck: Deck): Player {
        return copy(cards = cards + deck.draw(INITIAL_CARD_COUNT))
    }

    override fun hit(deck: Deck): Player {
        check(canHit()) { "카드를 받을 수 없습니다." }
        return copy(cards = cards + deck.draw())
    }
}
