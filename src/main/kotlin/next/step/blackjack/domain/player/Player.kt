package next.step.blackjack.domain.player

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.player.state.HitAvailableState
import next.step.blackjack.domain.player.state.PlayerState

data class Player(private val name: PlayerName, private val cards: PlayerCards, private var state: PlayerState) {

    init {
        require(cards.size() == INIT_CARD_CNT) { "플레이어는 처음에 카드 2장을 받아야 합니다." }
    }

    fun name() = name.name

    fun hit(card: Card) {
        cards.add(card)
        state = state.next(cards)
    }

    fun canHit(): Boolean = state.canHit()

    fun cardDescs(): Set<String> = cards.descs()

    fun point(): Int = cards.point()

    companion object {
        const val INIT_CARD_CNT = 2

        fun of(name: PlayerName, cards: PlayerCards): Player =
            Player(name, cards, HitAvailableState.next(cards))

        fun of(name: PlayerName, cardGenerator: (n: Int) -> List<Card>): Player {
            val cards = PlayerCards.of(cardGenerator(INIT_CARD_CNT))
            return Player(name, cards, HitAvailableState.next(cards))
        }
    }
}
