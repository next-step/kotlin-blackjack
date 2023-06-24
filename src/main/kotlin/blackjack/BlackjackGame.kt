package blackjack

import blackjack.domain.Players
import blackjack.domain.card.CardDeck

class BlackjackGame(
    private var turn: Int = -1,
    val players: Players,
    val cardDeck: CardDeck = CardDeck.randomCardDeck(),
) {
    fun firstDraw() {
        check(turn == BEFORE_FIRST_DRAW_TURN) { "first draw 턴이 아닙니다." }

    }

    companion object {
        private const val BEFORE_FIRST_DRAW_TURN = -1
    }
}
