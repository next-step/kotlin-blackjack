package blackjack.model

import blackjack.model.participant.BlackjackPlayer
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec

@DisplayName("블랙잭 플레이어 결과")
class BlackjackPlayerResultTest : StringSpec({

    "플레이어와 수익금으로 생성" {
        // given
        val player = BlackjackPlayer(CardDeck(), { _ -> Money(1000) }, PlayerName("name"), { _ -> }, { _ -> false })
        val revenue = Money(1000)
        // when & then
        shouldNotThrowAny {
            BlackjackPlayerResult(player, revenue)
        }
    }
})
