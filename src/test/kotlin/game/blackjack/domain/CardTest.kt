package game.blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드")
internal class CardTest {

    @Test
    fun `무늬와 끗수로 카드 생성`() {
        val expectedSuit = Suit.CLOVER
        val expectedDenomination = Denomination.ACE
        val card = Card(expectedSuit, expectedDenomination)

        assertThat(card.suit).isEqualTo(expectedSuit)
        assertThat(card.denomination).isEqualTo(expectedDenomination)
    }

    @Test
    fun `단순 합 계산`() {
        val ranks = listOf(
            Card(Suit.SPADE, Denomination.TEN),
            Card(Suit.SPADE, Denomination.TWO),
            Card(Suit.SPADE, Denomination.JACK),
        )
        val score = Card.score(ranks)
        assertThat(score).isEqualTo(22)
    }

    @Test
    fun `에이스가 1이어도 11이어도 21이 안되는 경우는 11로 계산`() {
        val ranks = listOf(
            Card(Suit.SPADE, Denomination.ACE),
            Card(Suit.SPADE, Denomination.TWO),
            Card(Suit.HEART, Denomination.TWO),
        )
        val score = Card.score(ranks)
        assertThat(score).isEqualTo(15)
    }

    @Test
    fun `에이스가 1이고 합이 21`() {
        val ranks = listOf(
            Card(Suit.SPADE, Denomination.ACE),
            Card(Suit.SPADE, Denomination.JACK),
            Card(Suit.SPADE, Denomination.QUEEN),
        )
        val score = Card.score(ranks)
        assertThat(score).isEqualTo(21)
    }

    @Test
    fun `에이스가 11이고 합이 21`() {
        val ranks = listOf(
            Card(Suit.SPADE, Denomination.ACE),
            Card(Suit.SPADE, Denomination.FOUR),
            Card(Suit.SPADE, Denomination.SIX),
        )
        val score = Card.score(ranks)
        assertThat(score).isEqualTo(21)
    }

    @Test
    fun `블랙잭`() {
        val ranks = listOf(
            Card(Suit.SPADE, Denomination.ACE),
            Card(Suit.SPADE, Denomination.KING),
        )
        val score = Card.score(ranks)
        assertThat(score).isEqualTo(21)
    }

    @Test
    fun `에이스만 2개면 12`() {
        val ranks = listOf(
            Card(Suit.SPADE, Denomination.ACE),
            Card(Suit.SPADE, Denomination.ACE),
        )
        val score = Card.score(ranks)
        assertThat(score).isEqualTo(12)
    }

    @Test
    fun `에이스만 3개면 13`() {
        val ranks = listOf(
            Card(Suit.SPADE, Denomination.ACE),
            Card(Suit.SPADE, Denomination.ACE),
            Card(Suit.SPADE, Denomination.ACE),
        )
        val score = Card.score(ranks)
        assertThat(score).isEqualTo(13)
    }

    @Test
    fun `에이스만 4개면 14`() {
        val ranks = listOf(
            Card(Suit.SPADE, Denomination.ACE),
            Card(Suit.SPADE, Denomination.ACE),
            Card(Suit.SPADE, Denomination.ACE),
            Card(Suit.SPADE, Denomination.ACE),
        )
        val score = Card.score(ranks)
        assertThat(score).isEqualTo(14)
    }
}
