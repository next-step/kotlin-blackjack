package blackjack.domain

import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import blackjack.model.Card

class GamePlayer(override val name: String, override val cards: Cards = Cards()) : Player {
    init {
        require(name.isNotBlank()) { "Player 이름은 공백이 될 수 없습니다. 유효한 Player 명을 입력해주세요." }
    }

    override fun readyToPlay(initialCards: List<Card>) {
        require(initialCards.size == INITIAL_CARDS_COUNT) { "잘못된 초기 카드 개수 입니다. 최초 2장만 카드를 받을 수 있습니다." }
        initialCards.forEach(cards::add)
    }

    override fun hit(card: Card) = cards.add(card)

    override fun sumCards(): Int = cards.sum()

    override fun burst(): Boolean = cards.sum() > BLACKJACK_SCORE

    override fun blackjack(): Boolean =
        cards.size == INITIAL_CARDS_COUNT && cards.sum() == BLACKJACK_SCORE
}
