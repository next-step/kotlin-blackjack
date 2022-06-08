package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Player(
    val name: String,
    cards: Cards = Cards.empty(),
    private var playerStatus: PlayerStatus = PlayerStatus.INIT
) {
    private val _cards: Cards = cards
    private val cardStatus get() = CardStatus.of(score)
    val cards get() = _cards.cards.toList()
    val score get() = _cards.score()
    val hittable get() = playerStatus == PlayerStatus.HIT && cardStatus != CardStatus.BUST

    fun initHandOut(cards: List<Card>) {
        check(playerStatus == PlayerStatus.INIT) {
            "초기 상태에서만 실행할 수 있습니다"
        }

        cards.forEach { _cards.add(it) }
        playerStatus = PlayerStatus.READY
    }

    fun handOut(card: Card) {
        check(playerStatus == PlayerStatus.HIT) {
            "HIT 상태가 아니면 카드를 추가할 수 없습니다"
        }
        validateBust()

        _cards.add(card)
        playerStatus = PlayerStatus.READY
    }

    fun hit() {
        validateReady()
        validateBust()

        playerStatus = PlayerStatus.HIT
    }

    fun stay() {
        validateReady()
        validateBust()

        playerStatus = PlayerStatus.STAY
    }

    private fun validateReady() {
        check(playerStatus == PlayerStatus.READY) {
            "READY 아니면 상태를 변경할 수 없습니다"
        }
    }

    private fun validateBust() {
        check(cardStatus != CardStatus.BUST) {
            "BUST 에서는 상태를 변경할 수 없습니다."
        }
    }

    companion object {
        fun ofList(value: String, delimiter: String = ","): List<Player> {
            val names = value.split(delimiter)
            return names.map(::Player)
        }
    }
}
