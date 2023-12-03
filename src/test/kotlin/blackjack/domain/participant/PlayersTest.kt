package blackjack.domain.participant

import blackjack.helper.DeckHelper
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.StringSpec

class PlayersTest : StringSpec({

    "플레이어가 1명 이하면 예외가 발생한다." {
        // given
        val deck = DeckHelper.createMockDeck()
        val dealer = Dealer(deck)

        // expected
        shouldThrowWithMessage<IllegalArgumentException>("플레이어는 최소 2명 이상이어야 합니다.") {
            Players.create(
                playerNames = listOf("플레이어1"),
                dealer = dealer,
            )
        }
    }
})
