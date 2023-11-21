package blackjack.model.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {
    @Test
    fun `카드 덱에서 카드 한장을 받는다`() {
        // given
        val cardDeck = CardDeck(
            mutableListOf(
                Card(CardNumber.ACE, CardSuit.클럽)
            )
        )

        // when
        val result = cardDeck.pop()

        // then
        result shouldBe Card(CardNumber.ACE, CardSuit.클럽)
    }

    @Test
    fun `카드 덱에서 카드 한장을 받으면 덱의 카드 수가 1 감소한다`() {
        // given
        val cardDeck = CardDeck(
            mutableListOf(
                Card(CardNumber.ACE, CardSuit.클럽)
            )
        )
        val beforeSize = cardDeck.deck.size

        // when
        cardDeck.pop()
        val afterSize = cardDeck.deck.size

        // then
        beforeSize - 1 shouldBe afterSize
    }

    @Test
    fun `카드 덱에 카드를 추가하면 덱의 카드 수가 1 증가한다`() {
        // given
        val cardDeck = CardDeck()
        val beforeSize = cardDeck.deck.size

        // when
        cardDeck.add(Card(CardNumber.ACE, CardSuit.클럽))
        val afterSize = cardDeck.deck.size

        // then
        beforeSize + 1 shouldBe afterSize
    }

    @Test
    fun `카드 덱에 카드가 없는 경우 카드를 받을 수 없다`() {
        // given
        val cardDeck = CardDeck()

        // when & then
        assertThrows<IllegalStateException> {
            cardDeck.pop()
        }
    }

    @Test
    fun `카드 덱에 이미 존재하는 카드를 받을 수 없다`() {
        // given
        val cardDeck = CardDeck(
            mutableListOf(
                Card(CardNumber.ACE, CardSuit.클럽)
            )
        )

        // when & then
        assertThrows<IllegalStateException> {
            cardDeck.add(Card(CardNumber.ACE, CardSuit.클럽))
        }
    }

    @Test
    fun `카드 덱의 합이 21보다 큰 경우 버스트이다`() {
        // given
        val cardDeck = CardDeck(
            mutableListOf(
                Card(CardNumber.KING, CardSuit.클럽),
                Card(CardNumber.KING, CardSuit.다이아몬드),
                Card(CardNumber.KING, CardSuit.스페이드),
            )
        )

        // when & then
        cardDeck.isBust() shouldBe true
    }

    @Test
    fun `카드 덱의 합이 21보다 같거나 작은 경우 스테이이다`() {
        // given
        val cardDeck = CardDeck(
            mutableListOf(
                Card(CardNumber.KING, CardSuit.클럽)
            )
        )

        // when & then
        cardDeck.isStay() shouldBe true
    }

    @Test
    fun `카드 덱의 갯수가 2개이고 합이 21이면 블랙잭이다`() {
        // given
        val cardDeck = CardDeck(
            mutableListOf(
                Card(CardNumber.ACE, CardSuit.클럽),
                Card(CardNumber.KING, CardSuit.클럽)
            )
        )

        // when & then
        cardDeck.isBlackJack() shouldBe true
    }

    @Test
    fun `카드 덱의 합이 21을 초과하고 에이스가 있는 경우 에이스를 1로 계산한다`() {
        // given
        val cardDeck = CardDeck(
            mutableListOf(
                Card(CardNumber.KING, CardSuit.클럽),
                Card(CardNumber.KING, CardSuit.다이아몬드),
                Card(CardNumber.ACE, CardSuit.스페이드)
            )
        )

        // when & then
        cardDeck.calculateScore() shouldBe 21
    }

    @Test
    fun `카드 덱의 합이 21을 초과하지 않고 에이스가 있는 경우 에이스를 11로 계산한다`() {
        // given
        val cardDeck = CardDeck(
            mutableListOf(
                Card(CardNumber.ACE, CardSuit.스페이드)
            )
        )

        // when & then
        cardDeck.calculateScore() shouldBe 11
    }
}
