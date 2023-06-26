package next.step.blackjack.domain.player

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.card.state.CardsState
import next.step.blackjack.domain.card.state.UnfinishedState
import next.step.blackjack.domain.game.Fightable
import next.step.blackjack.domain.game.GameResult

open class Player(
    protected val name: PlayerName,
    protected val cards: Cards = Cards.of(emptyList()),
    protected var state: CardsState = UnfinishedState
) : Fightable<Player> {

    fun name(): String = name.name

    fun hit(card: Card) {
        cards.add(card)
        state = state.next(cards)
    }

    open fun canHit(): Boolean = state == UnfinishedState

    fun cardDescs(): Set<String> = cards.descs()

    fun point(): Int = cards.point()

    override fun fight(other: Player): GameResult {
        val gameResult = state.fight(other.state)
        return if (gameResult == GameResult.UNDECIDED) cards.fight(other.cards) else gameResult
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (name != other.name) return false
        if (cards != other.cards) return false
        return state == other.state
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + cards.hashCode()
        result = 31 * result + state.hashCode()
        return result
    }

    companion object {
        fun of(name: PlayerName, cards: Cards): Player =
            Player(name, cards, UnfinishedState.next(cards))
    }
}
