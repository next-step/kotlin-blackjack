package blackjack.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

@DisplayName("손에 들고 있는 덱")
class HandDeckTest : StringSpec({

    "카드들로 생성" {
        listOf(
            emptyList(), listOf(SPADE_ACE),
        ).forAll {
            shouldNotThrowAny {
                HandDeck(it)
            }
        }
    }

    "카드 추가 가능" {
        // given
        val spadeAceDeck = HandDeck(listOf(SPADE_ACE))
        // when
        val addedDeck: HandDeck = spadeAceDeck + SPADE_KING
        // then
        addedDeck shouldBe HandDeck(listOf(SPADE_ACE, SPADE_KING))
    }

    "스페이드 에이스 카드 세기" {
        listOf(
            listOf(SPADE_KING) to 0,
            listOf(SPADE_ACE) to 1,
            listOf(SPADE_ACE, HEART_ACE) to 2
        ).forAll {
            HandDeck(it.first).aceCount shouldBe it.second
        }
    }

    "원하는 대상으로 합산 가능" {
        HandDeck(listOf(SPADE_ACE)).sumOf { 0 } shouldBe 0
    }
})
