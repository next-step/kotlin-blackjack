package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.score.CardScore

open class Player(
    val name: String,
    cards: Cards = Cards.empty(),
    private var playerStatus: PlayerStatus? = null
) {
    private val _cards: Cards = cards
    private val cardScore get() = CardScore.of(score)
    val cards get() = _cards.cards.toList()
    val score get() = _cards.score()
    val isEnd get() = playerStatus == PlayerStatus.STAY || cardScore == CardScore.BUST

    fun addCard(card: Card) {
        check(!isEnd) {
            "STAY 또는 BUST 에서는 카드를 추가할 수 없습니다."
        }

        _cards.add(card)
    }

    fun changeStatus(status: PlayerStatus) {
        check(!isEnd) {
            "STAY 또는 BUST 에서는 상태를 변경할 수 없습니다."
        }

        playerStatus = status
    }

    companion object {
        fun ofList(value: String, delimiter: String = ","): List<Player> {
            val names = value.split(delimiter)
            return names.map(::Player)
        }
    }
}
