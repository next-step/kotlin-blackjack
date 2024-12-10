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
                Card(CardNumber.ACE, CardSuit.CLOVER),
                Card(CardNumber.ACE, CardSuit.DIAMOND),
                Card(CardNumber.JACK, CardSuit.CLOVER),
                Card(CardNumber.EIGHT, CardSuit.CLOVER),
            )

        playerCardDeck.calculateScore()
        playerCardDeck.score.value shouldBe 20
    }

    @Test
    fun `플레이어 카드 더미에 카드를 추가할 수 있다`() {
        val playerCardDeck =
            PlayerCardDeck(
                Card(CardNumber.ACE, CardSuit.CLOVER),
                Card(CardNumber.ACE, CardSuit.DIAMOND),
                Card(CardNumber.JACK, CardSuit.CLOVER),
                Card(CardNumber.EIGHT, CardSuit.CLOVER),
            )

        playerCardDeck.addCard(Card(CardNumber.KING, CardSuit.CLOVER))

        playerCardDeck.cards shouldContain Card(CardNumber.KING, CardSuit.CLOVER)
    }
}
