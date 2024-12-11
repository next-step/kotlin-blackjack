package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardSuit
import blackjack.domain.PlayerCardDeck
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerCardDeckTest {
    @Test
    fun `플레이어 카드 더미에서 나올 수 있는 합의 경우를 알 수 있다`() {
        val playerCardDeck =
            PlayerCardDeck(
                Card(CardNumber.JACK, CardSuit.CLOVER),
                Card(CardNumber.EIGHT, CardSuit.CLOVER),
                Card(CardNumber.ACE, CardSuit.CLOVER),
                Card(CardNumber.ACE, CardSuit.DIAMOND),
            )
        playerCardDeck.calculateScore()
        playerCardDeck.score.value shouldBe 20
    }

    @Test
    fun `ACE 카드가 2장 일 경우 12가 나온다`() {
        val playerCardDeck =
            PlayerCardDeck(
                Card(CardNumber.ACE, CardSuit.SPADE),
                Card(CardNumber.ACE, CardSuit.HEART),
            )

        playerCardDeck.calculateScore()
        playerCardDeck.score.value shouldBe 12
    }

    @Test
    fun `ACE 카드가 3장 일 경우 13가 나온다`() {
        val playerCardDeck =
            PlayerCardDeck(
                Card(CardNumber.ACE, CardSuit.SPADE),
                Card(CardNumber.ACE, CardSuit.HEART),
                Card(CardNumber.ACE, CardSuit.DIAMOND),
            )

        playerCardDeck.calculateScore()
        playerCardDeck.score.value shouldBe 13
    }

    @Test
    fun `ACE 카드가 4장 일 경우 14가 나온다`() {
        val playerCardDeck =
            PlayerCardDeck(
                Card(CardNumber.ACE, CardSuit.SPADE),
                Card(CardNumber.ACE, CardSuit.HEART),
                Card(CardNumber.ACE, CardSuit.CLOVER),
                Card(CardNumber.ACE, CardSuit.DIAMOND),
            )

        playerCardDeck.calculateScore()
        playerCardDeck.score.value shouldBe 14
    }

    @Test
    fun `ACE 카드가 4장, 숫자카드가 6일 경우 20이 나온다`() {
        val playerCardDeck =
            PlayerCardDeck(
                Card(CardNumber.ACE, CardSuit.SPADE),
                Card(CardNumber.ACE, CardSuit.HEART),
                Card(CardNumber.ACE, CardSuit.CLOVER),
                Card(CardNumber.ACE, CardSuit.DIAMOND),
                Card(CardNumber.SIX, CardSuit.SPADE),
            )

        playerCardDeck.calculateScore()
        playerCardDeck.score.value shouldBe 20
    }

    @Test
    fun `ACE 카드가 4장, 숫자카드가 7 일 경우 21이 나온다`() {
        val playerCardDeck =
            PlayerCardDeck(
                Card(CardNumber.ACE, CardSuit.SPADE),
                Card(CardNumber.ACE, CardSuit.HEART),
                Card(CardNumber.ACE, CardSuit.CLOVER),
                Card(CardNumber.ACE, CardSuit.DIAMOND),
                Card(CardNumber.SEVEN, CardSuit.SPADE),
            )

        playerCardDeck.calculateScore()
        playerCardDeck.score.value shouldBe 21
    }

    @Test
    fun `ACE 카드가 4장, 숫자카드가 8 일 경우 12 나온다`() {
        val playerCardDeck =
            PlayerCardDeck(
                Card(CardNumber.ACE, CardSuit.SPADE),
                Card(CardNumber.ACE, CardSuit.HEART),
                Card(CardNumber.ACE, CardSuit.CLOVER),
                Card(CardNumber.ACE, CardSuit.DIAMOND),
                Card(CardNumber.EIGHT, CardSuit.SPADE),
            )

        playerCardDeck.calculateScore()
        playerCardDeck.score.value shouldBe 12
    }

    @Test
    fun `플레이어 카드 더미에 카드를 추가할 수 있다`() {
        val playerCardDeck =
            PlayerCardDeck(
                Card(CardNumber.JACK, CardSuit.CLOVER),
                Card(CardNumber.EIGHT, CardSuit.CLOVER),
                Card(CardNumber.ACE, CardSuit.CLOVER),
                Card(CardNumber.ACE, CardSuit.DIAMOND),
            )

        playerCardDeck.addCard(Card(CardNumber.KING, CardSuit.CLOVER))

        playerCardDeck.cards shouldContain Card(CardNumber.KING, CardSuit.CLOVER)
    }
}
