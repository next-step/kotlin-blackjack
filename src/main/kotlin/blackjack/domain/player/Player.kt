package blackjack.domain.player

import blackjack.domain.Score
import blackjack.domain.card.CardDeck
import blackjack.domain.player.vo.Name

class Player private constructor(
    val name: Name,
    val cardsInHand: CardsInHand
) {
    var isStay: Boolean = false
        get() = field || cardsInHand.calculateScore() >= Score.BLACKJACK
        private set

    fun hit(cardDeck: CardDeck) {
        require(!isStay) { "Stay를 선언하였다면 카드를 뽑을수 없습니다." }
        require(cardsInHand.calculateScore() < Score.BLACKJACK) {
            "현재 점수가 ${Score.BLACKJACK.value} 보다 크거나 같으면 카드를 뽑지 못합니다."
        }

        cardsInHand.add(cardDeck.draw())
    }

    fun stay() {
        isStay = true
    }

    companion object {
        fun sit(name: Name): Player = Player(name, CardsInHand(emptyList()))
    }
}
