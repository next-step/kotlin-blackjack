package blackjack.domain

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `CardDeck에 모든 카드가 준비되어 있지 않으면 에러가 발생한다`() {
        shouldThrowWithMessage<IllegalArgumentException>(message = "블랙잭 게임을 하기 위해서는 모든 카드가 준비되어야합니다") {
            CardDeck(mutableListOf(Card(Suit.DIAMONDS, Rank.ACE), Card(Suit.HEARTS, Rank.NINE)))
        }
    }

    @Test
    fun `Deck에 더이상 카드가 남아있지 않을 때 카드를 꺼내면 에러가 발생한다`() {
        val cardDeck = CardDeck(defaultDeckGenerator())
        shouldThrowWithMessage<IllegalArgumentException>(message = "더이상 카드가 남아있지 않습니다") {
            repeat(53) { cardDeck.drawCard() }
        }
    }

    @Test
    fun `Deck에서 한번 꺼낸 카드는 더이상 Deck에 남아있지 않아야한다`() {
        val cardDeck = CardDeck(defaultDeckGenerator())
        val card = cardDeck.drawCard()
        cardDeck.deck.contains(card) shouldBeEqual false
    }
}
