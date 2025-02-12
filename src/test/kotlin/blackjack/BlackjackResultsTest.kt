package blackjack

import blackjack.domain.BlackjackResults
import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Rank
import blackjack.domain.Suit
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackjackResultsTest {
    @Test
    fun `딜러가 참가자보다 점수가 높으면 참가자가 패배`() {
        val blackjackResults = BlackjackResults(
            Dealer().apply {
                receiveCard(Card(Suit.SPADE, Rank.KING))
                receiveCard(Card(Suit.SPADE, Rank.KING))
            },
            listOf(
                Participant("a").apply {
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                    receiveCard(Card(Suit.SPADE, Rank.NINE))
                    bet(10000)
                }
            )
        )

        val actual = blackjackResults.result.first().money
        actual.shouldNotBeNull()
        actual shouldBe 10000
    }

    @Test
    fun `딜러가 참가자보다 점수가 낮으면 참가자가 승리`() {
        val blackjackResults = BlackjackResults(
            Dealer().apply {
                receiveCard(Card(Suit.SPADE, Rank.KING))
                receiveCard(Card(Suit.SPADE, Rank.NINE))
            },
            listOf(
                Participant("a").apply {
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                    bet(10000)
                }
            )
        )

        val actual = blackjackResults.result.first().money
        actual.shouldNotBeNull()
        actual shouldBe -10000
    }

    @Test
    fun `딜러가 참가자와 점수가 동일하면 무승부`() {
        val blackjackResults = BlackjackResults(
            Dealer().apply {
                receiveCard(Card(Suit.SPADE, Rank.KING))
                receiveCard(Card(Suit.SPADE, Rank.KING))
            },
            listOf(
                Participant("a").apply {
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                    bet(10000)
                }
            )
        )

        val actual = blackjackResults.result.first().money
        actual.shouldNotBeNull()
        actual shouldBe 0
    }

    @Test
    fun `딜러가 버스트면 참가자의 승`() {
        val blackjackResults = BlackjackResults(
            Dealer().apply {
                receiveCard(Card(Suit.SPADE, Rank.KING))
                receiveCard(Card(Suit.SPADE, Rank.KING))
                receiveCard(Card(Suit.SPADE, Rank.KING))
            },
            listOf(
                Participant("a").apply {
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                    bet(10000)
                },
            )
        )

        val actual = blackjackResults.result.first().money
        actual.shouldNotBeNull()
        actual shouldBe -10000
    }

    @Test
    fun `참가자가 버스트면 딜러가 승`() {
        val blackjackResults = BlackjackResults(
            Dealer().apply {
                receiveCard(Card(Suit.SPADE, Rank.KING))
                receiveCard(Card(Suit.SPADE, Rank.KING))
            },
            listOf(
                Participant("a").apply {
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                    bet(10000)
                },
            )
        )

        val actual = blackjackResults.result.first().money
        actual.shouldNotBeNull()
        actual shouldBe 10000
    }
}
