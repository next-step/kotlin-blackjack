package blackjack.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

@DisplayName("손에 들고 있는 덱")
class HandDeckTest : StringSpec({

    "카드들로 생성" {
        shouldNotThrowAny {
            HandDeck()
        }
    }

    "카드 추가 가능" {
        // given
        val handDeck = HandDeck()
        // when
        handDeck.add(SPADE_KING)
        // then
        handDeck.score shouldBe 10
    }

    "21점 카드 기준 점수 이하는 에이스 카드 점수 그대로 반환" {
        listOf(
            emptyList<TrumpCard>() to 0,
            listOf(SPADE_ACE, DIAMOND_TWO) to 13,
            listOf(CLOVER_THREE, DIAMOND_TWO) to 5,
            listOf(SPADE_ACE, CLOVER_THREE) to 14,
            listOf(HEART_ACE, DIAMOND_TWO, CLOVER_THREE) to 16,
            listOf(SPADE_ACE, SPADE_KING, SPADE_TEN, CLOVER_THREE) to 24,
        ).forAll {
            // given
            val handDeck = HandDeck()
            it.first.forEach(handDeck::add)
            // when & then
            handDeck.score shouldBe it.second
        }
    }

    "21점 카드 기준 점수 이상은 ace 를 1로 계산" {
        listOf(
            listOf(SPADE_ACE, HEART_ACE) to 12,
            listOf(SPADE_KING, SPADE_TEN, SPADE_ACE) to 21,
            listOf(HEART_ACE, SPADE_KING, DIAMOND_TWO, CLOVER_THREE) to 16,
        ).forAll {
            // given
            val handDeck = HandDeck()
            it.first.forEach(handDeck::add)
            // when & then
            handDeck.score shouldBe it.second
        }
    }
})
