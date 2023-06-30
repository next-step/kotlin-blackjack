package blackjack.model

import blackjack.model.participant.BlackjackPlayer
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

@DisplayName("플레이어 이름들")
class PlayerNamesTest : StringSpec({

    "이름들로 사람 생성 가능" {
        // given
        val playerNames = PlayerNames(listOf(PlayerName("first"), PlayerName("second")))
        // when
        val players: Collection<BlackjackPlayer> = playerNames
            .registerPlayer(
                CardDeck(), { _ -> Money(1000) }, { _ -> }, { _ -> false }
            )
        // then
        players.size shouldBe 2
    }
})
