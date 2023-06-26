package blackjack.model

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual

@DisplayName("블랙잭 플레이어")
class BlackjackPlayerTest : StringSpec({

    "이름과 카드 컨슈머, 프레디케이트로 생성" {
        shouldNotThrowAny {
            BlackjackPlayer(
                PlayerName("name"), CardDeck(), { _ -> }, { _ -> false }
            )
        }
    }

    "21점 이하인 경우 추가 카드를 뽑을 수 있음" {
        // given
        val player = BlackjackPlayer(
            PlayerName("name"), CardDeck(), { _ -> }, { _ -> true }
        )
        // when
        player.draw(CardDeck())
        // then
        player.deckScore shouldBeGreaterThanOrEqual 21
    }
})
