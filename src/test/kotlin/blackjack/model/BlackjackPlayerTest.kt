package blackjack.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*

@DisplayName("블랙잭 플레이어")
class BlackjackPlayerTest : StringSpec({

    "이름과 카드 리스트로 생성" {
        shouldNotThrowAny {
            BlackjackPlayer("name", listOf(TrumpCard(TrumpCardShape.SPADE, TrumpCardNumber.ACE)))
        }
    }

    "이름은 공백일 수 없음" {
        shouldThrowExactly<IllegalArgumentException> {
            BlackjackPlayer("", emptyList())
        }
    }

    "스페이드 에이스 카드 추가" {
        // given
        val name = "name"
        val emptyCardsPlayer = BlackjackPlayer(name, emptyList())
        // when
        val addedCardPlayer: BlackjackPlayer = emptyCardsPlayer.addedCard(SPADE_ACE)
        // then
        addedCardPlayer shouldBe BlackjackPlayer(name, listOf(SPADE_ACE))
    }
})
