package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Deck
import blackjack.domain.status.Stay
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class PlayerTest : StringSpec({
    "chooseHitOrStay()에서 Stay을 선택한 경우, status가 Stay로 변환된다" {
        val player = Player("testUser")

        player.chooseHitOrStay(false, Deck.create())

        player.status.shouldBeInstanceOf<Stay>()
    }
})
