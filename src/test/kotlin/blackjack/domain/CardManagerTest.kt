package blackjack.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class CardManagerTest : StringSpec({

    val mockCardGenerator = mockk<CardGenerator>()

    "초기화 시 카드가 없으면 에러 발생" {
        // given
        val cards = emptyList<Card>()
        every { mockCardGenerator.generate() } returns cards
        // when
        val exception = shouldThrowExactly<IllegalArgumentException> {
            CardManager(mockCardGenerator)
        }
        // then
        exception.message shouldBe "카드가 없습니다."
    }

    "카드 뽑기 테스트" {
        // given
        val cards = listOf(
            Card(CardSuit.SPADE, CardNumber.KING),
            Card(CardSuit.DIAMOND, CardNumber.ACE),
            Card(CardSuit.CLUB, CardNumber.THREE)
        )
        every { mockCardGenerator.generate() } returns cards
        val cardManager = CardManager(mockCardGenerator)
        // when
        val first = cardManager.getCard()
        val second = cardManager.getCard()
        val third = cardManager.getCard()
        // then
        first shouldBe Card(CardSuit.CLUB, CardNumber.THREE)
        second shouldBe Card(CardSuit.DIAMOND, CardNumber.ACE)
        third shouldBe Card(CardSuit.SPADE, CardNumber.KING)
    }

    "카드 유무 테스트" {
        TODO()
    }
})
