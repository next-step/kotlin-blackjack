package next.step.blackjack.domain.player

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.card.PlayerCards
import next.step.blackjack.domain.game.GameResult

open class Player(
    protected val name: PlayerName,
    protected val cards: PlayerCards = PlayerCards()
) {

    fun name(): String = name.name

    fun hit(card: Card) = cards.hit(card)

    open fun canHit(): Boolean = cards.isUnfinished()

    fun cardDescs(): Set<String> = cards.descs()

    fun cards(): List<Card> = cards.cards()

    fun point(): Int = cards.point()

    fun fight(other: Player): GameResult = cards.fight(other.cards)

    companion object {
        fun of(name: PlayerName, cards: Cards): Player =
            Player(name, PlayerCards.of(cards))
    }
}
