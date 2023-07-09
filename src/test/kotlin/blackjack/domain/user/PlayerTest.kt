package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Deck
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "chooseHitOrStay()에서 Stay을 선택한 경우, isDone()이 true를 반환한다" {
        val player = Player("testUser")

        player.chooseHitOrStay(false, Deck.create())

        player.isDone() shouldBe true
    }
})
