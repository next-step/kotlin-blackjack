package blackJack.domain

import blackJack.domain.Rank.*
import blackJack.domain.Status.*
import blackJack.domain.Suit.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StatusTest {

    @Test
    fun `totalScore 점수가 21 보다 크면 BUST 상태를 반환`() {
        val cards = Cards(mutableListOf(Card(DIAMOND, KING), Card(HEART, QUEEN), Card(CLUB, JACK)))
        val status = Status.calculateStatus(cards.calculateTotalScore(), cards.cardSize)
        assertEquals(BUST, status)
    }

    @Test
    fun `totalScore 점수가 21 이고 카드 갯수가 2개이면 BLACKJACK 상태를 반환`() {
        val cards = Cards(mutableListOf(Card(DIAMOND, ACE), Card(HEART, QUEEN)))
        val status = Status.calculateStatus(cards.calculateTotalScore(), cards.cardSize)
        assertEquals(BLACKJACK, status)
    }

    @Test
    fun `totalScore 점수가 21 이고 카드 갯수가 2개가 아니면 STAND 상태를 반환`() {
        val cards = Cards(mutableListOf(Card(DIAMOND, ACE), Card(HEART, EIGHT), Card(CLUB, TWO)))
        val status = Status.calculateStatus(cards.calculateTotalScore(), cards.cardSize)
        assertEquals(STAND, status)
    }

    @Test
    fun `totalScore 점수가 21 보다 작으면 HIT 상태를 반환`() {
        val cards = Cards(mutableListOf(Card(DIAMOND, ACE), Card(DIAMOND, NINE)))
        val status = Status.calculateStatus(cards.calculateTotalScore(), cards.cardSize)
        assertEquals(HIT, status)
    }

    @Test
    fun `카드를 추가하려고 할 때 status 가 HIT 이면 Exception 이 발생하지 않는다`() {
        Status.validationAddCard(HIT)
    }

    @Test
    fun `카드를 추가하려고 할 때 status 가 BUST 이면 Exception 발생`() {
        assertThrows<IllegalArgumentException> {
            Status.validationAddCard(BUST)
        }
    }

    @Test
    fun `카드를 추가하려고 할 때 status 가 STAND 이면 Exception 발생`() {
        assertThrows<IllegalArgumentException> {
            Status.validationAddCard(STAND)
        }
    }

    @Test
    fun `카드를 추가하려고 할 때 status 가 BLACKJACK 이면 Exception 발생`() {
        assertThrows<IllegalArgumentException> {
            Status.validationAddCard(BLACKJACK)
        }
    }
}
