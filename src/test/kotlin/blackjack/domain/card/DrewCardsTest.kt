package blackjack.domain.card

import blackjack.domain.rule.DefaultScoringRule
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DrewCardsTest {

    @Test
    fun `DrewCards 객체는 뽑은 Card 객체의 리스트를 갖는 객체이고, 점수 계산 규칙으로 초기화한다`() {
        val drewCards = DrewCards(DefaultScoringRule())

        drewCards.size shouldBe 0
    }

    @Test
    fun `DrewCards 객체는 뽑은 Card를 추가할 수 있다`() {
        val drewCards = DrewCards(DefaultScoringRule())

        drewCards.add(Card(CardCharacter.ACE, CardShape.SPADE))

        drewCards.size shouldBe 1
        drewCards.totalScore shouldBeGreaterThan 0
        drewCards.state shouldBe State.BEGIN
        drewCards.isFinished shouldBe false
    }

    @Test
    fun `현재 가지고 있는 카드의 점수를 계산 - 최초는 0점`() {
        val drewCards = DrewCards(DefaultScoringRule())

        drewCards.size shouldBe 0
        drewCards.totalScore shouldBe 0
        drewCards.state shouldBe State.BEGIN
        drewCards.isFinished shouldBe false
    }

    @Test
    fun `현재 가지고 있는 카드의 점수를 계산 - 카드를 갖는 경우`() {
        val drewCards = DrewCards(DefaultScoringRule())

        drewCards.add(Card(CardCharacter.ACE, CardShape.SPADE))
        drewCards.add(Card(CardCharacter.SEVEN, CardShape.HEART))
        drewCards.add(Card(CardCharacter.KING, CardShape.SPADE))

        drewCards.size shouldBe 3
        drewCards.totalScore shouldBe 18
        drewCards.state shouldBe State.HIT
        drewCards.isFinished shouldBe false
    }

    @Test
    fun `BLACKJACK 상태인 경우`() {
        val drewCards = DrewCards(DefaultScoringRule())

        drewCards.add(Card(CardCharacter.ACE, CardShape.SPADE))
        drewCards.add(Card(CardCharacter.KING, CardShape.SPADE))

        drewCards.size shouldBe 2
        drewCards.totalScore shouldBe DefaultScoringRule.THRESHOLD_SCORE
        drewCards.state shouldBe State.BLACKJACK
        drewCards.isFinished shouldBe true
    }

    @Test
    fun `BUST 상태인 경우`() {
        val drewCards = DrewCards(DefaultScoringRule())

        drewCards.add(Card(CardCharacter.KING, CardShape.SPADE))
        drewCards.add(Card(CardCharacter.KING, CardShape.SPADE))
        drewCards.add(Card(CardCharacter.SEVEN, CardShape.SPADE))

        drewCards.size shouldBe 3
        drewCards.totalScore shouldBe 27
        drewCards.state shouldBe State.BUST
        drewCards.isFinished shouldBe true
    }

    @Test
    fun `STAY 상태인 경우`() {
        val drewCards = DrewCards(DefaultScoringRule())

        drewCards.stay()

        drewCards.size shouldBe 0
        drewCards.totalScore shouldBe 0
        drewCards.state shouldBe State.STAY
        drewCards.isFinished shouldBe true
    }
}