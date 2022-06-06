package blackjack.domain.player

import blackjack.domain.Score
import blackjack.domain.card.CardDeck
import blackjack.domain.player.vo.Name

private const val READY_CARD_COUNT = 2

open class Player constructor(
    val name: Name,
    val cardsInHand: CardsInHand
) {
    private var stay: Boolean = false

    open fun isStay(): Boolean = stay || cardsInHand.calculateScore() >= Score.BLACKJACK

    open fun ready(cardDeck: CardDeck) {
        repeat(READY_CARD_COUNT) { cardsInHand.add(cardDeck.draw()) }
    }

    fun hit(cardDeck: CardDeck) {
        require(!stay) { "Stay를 선언하였다면 카드를 뽑을수 없습니다." }
        require(cardsInHand.calculateScore() < Score.BLACKJACK) {
            "현재 점수가 ${Score.BLACKJACK.value} 보다 크거나 같으면 카드를 뽑지 못합니다."
        }

        cardsInHand.add(cardDeck.draw())
    }

    fun stay() {
        stay = true
    }

    companion object {
        fun sit(name: Name): Player = Player(name, CardsInHand(emptyList()))
    }
}
