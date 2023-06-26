package next.step.blackjack.domain.player

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.card.state.CardsState
import next.step.blackjack.domain.card.state.UnfinishedState
import next.step.blackjack.domain.game.GameResult

open class Player(
    protected val name: PlayerName,
    protected val cards: Cards = Cards.of(emptyList()),
    protected var state: CardsState = UnfinishedState
) {

    fun name(): String = name.name

    fun hit(card: Card) {
        cards.add(card)
        state = state.next(cards)
    }

    open fun canHit(): Boolean = state == UnfinishedState

    fun cardDescs(): Set<String> = cards.descs()

    fun cards(): List<Card> = cards.cards()

    fun point(): Int = cards.point()

    fun fight(other: Player): GameResult {
        val gameResult = state.fight(other.state)
        return if (gameResult == GameResult.UNDECIDED) cards.fight(other.cards) else gameResult
    }

    companion object {
        fun of(name: PlayerName, cards: Cards): Player =
            Player(name, cards, UnfinishedState.next(cards))
    }
}
