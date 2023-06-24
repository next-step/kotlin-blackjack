package blackjack.gamestate

import blackjack.Card
import blackjack.Cards

class Bust(
    val cards: Cards,
) : GameState {
    override fun draw(card: Card) = throw IllegalStateException("종료된 게임은 draw할 수 없다.")

    override fun stay() = throw IllegalStateException("종료된 게임은 stay할 수 없다.")
}
