package blackjack.domain.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HandsTest {
    @Test
    fun `ACE와 TEN으로 점수를 계산하면 21점이다`() {
        val hands = Hands(Card(CardCharacter.ACE, CardShape.CLUB), Card(CardCharacter.TEN, CardShape.CLUB))
        hands.score().value shouldBe 21
    }

    @Test
    fun `ACE가 2장인 경우 점수를 계산하면 12점이다`() {
        val hands = Hands(Card(CardCharacter.ACE, CardShape.CLUB), Card(CardCharacter.ACE, CardShape.HEART))
        hands.score().value shouldBe 12
    }

    @Test
    fun `TEN, TEN, ACE를 가지고 있을 때 점수는 21점이다`() {
        val hands = Hands(
            Card(CardCharacter.ACE, CardShape.CLUB),
            Card(CardCharacter.TEN, CardShape.CLUB),
            Card(CardCharacter.TEN, CardShape.HEART)
        )
        hands.score().value shouldBe 21
    }
}
