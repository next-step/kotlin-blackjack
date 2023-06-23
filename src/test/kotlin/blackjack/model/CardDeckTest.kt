package blackjack.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

@DisplayName("카드 덱")
class CardDeckTest : StringSpec({

    "비어있지 않은 카드들로 덱 생성" {
        shouldNotThrowAny {
            CardDeck(listOf(SPADE_ACE))
        }
    }

    "카드를 선택할 수 있음" {
        // given
        val spadeAceDeck = CardDeck(listOf(SPADE_ACE))
        // when
        val card: TrumpCard = spadeAceDeck.card { cards -> cards.first() }
        // then
        card shouldBe SPADE_ACE
    }

    "카드가 비어있으면 카드를 선택할 수 없음" {
        // given
        val emptyDeck = CardDeck(emptyList())
        // when & then
        shouldThrowExactly<IllegalStateException> {
            emptyDeck.card { cards -> cards.first() }
        }
    }

    "대상 카드가 제거된 카드 덱을 생성" {
        // given
        val spadeAceDeck = CardDeck(listOf(SPADE_ACE))
        // when
        val minusDeck: CardDeck = spadeAceDeck - SPADE_ACE
        // then
        minusDeck shouldBe CardDeck(emptyList())
    }

    "대상 카드를 포함하고 있어야만 제거할 수 있음" {
        // given
        val emptyDeck = CardDeck(emptyList())
        // when
        shouldThrowExactly<IllegalStateException> {
            emptyDeck - SPADE_ACE
        }
    }
})
