package game.blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드 등급에 따른 점수")
internal class DenominationTest {

    @Test
    fun `단순 계산`() {
        val ranks = listOf(Denomination.TEN, Denomination.TWO, Denomination.JACK)
        val score = Denomination.score(ranks)
        assertThat(score).isEqualTo(22)
    }

    @Test
    fun `에이스가 1이어도 11이어도 21이 안되는 경우는 11로 계산`() {
        val ranks = listOf(Denomination.ACE, Denomination.TWO, Denomination.TWO)
        val score = Denomination.score(ranks)
        assertThat(score).isEqualTo(15)
    }

    @Test
    fun `에이스가 1이고 합이 21`() {
        val ranks = listOf(Denomination.ACE, Denomination.QUEEN, Denomination.QUEEN)
        val score = Denomination.score(ranks)
        assertThat(score).isEqualTo(21)
    }

    @Test
    fun `에이스가 11이고 합이 21`() {
        val ranks = listOf(Denomination.ACE, Denomination.FOUR, Denomination.SIX)
        val score = Denomination.score(ranks)
        assertThat(score).isEqualTo(21)
    }

    @Test
    fun `에이스만 2개면 12`() {
        val ranks = listOf(Denomination.ACE, Denomination.ACE)
        val score = Denomination.score(ranks)
        assertThat(score).isEqualTo(12)
    }

    @Test
    fun `에이스만 3개면 13`() {
        val ranks = listOf(Denomination.ACE, Denomination.ACE, Denomination.ACE)
        val score = Denomination.score(ranks)
        assertThat(score).isEqualTo(13)
    }

    @Test
    fun `에이스만 4개면 14`() {
        val ranks = listOf(Denomination.ACE, Denomination.ACE, Denomination.ACE, Denomination.ACE)
        val score = Denomination.score(ranks)
        assertThat(score).isEqualTo(14)
    }
}