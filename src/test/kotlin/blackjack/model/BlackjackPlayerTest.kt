package blackjack.model

import blackjack.model.participant.BlackjackPlayer
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual

@DisplayName("블랙잭 플레이어")
class BlackjackPlayerTest : StringSpec({

    "이름과 카드 컨슈머, 프레디케이트로 생성" {
        shouldNotThrowAny {
            BlackjackPlayer(
                CardDeck(), { _ -> Money(1000) }, PlayerName("name"), { _ -> }, { _ -> false }
            )
        }
    }

    "21점 이하인 경우 추가 카드를 뽑을 수 있음" {
        // given
        val player = BlackjackPlayer(
            CardDeck(), { _ -> Money(1000) }, PlayerName("name"), { _ -> }, { _ -> true }
        )
        // when
        player.draw()
        // then
        player.deckScore shouldBeGreaterThanOrEqual 21
    }
})
        