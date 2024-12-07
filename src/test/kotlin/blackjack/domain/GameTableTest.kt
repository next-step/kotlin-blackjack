package blackjack.domain

import blackjack.fixtures.createUsers
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameTableTest : StringSpec({
    "최초 딜 시 카드를 2장 나누어준다" {
        val initCardReceivedUsers = GameTable(createUsers(), Deck.create()).dealInitCard()

        initCardReceivedUsers.forEach {
            it.cards.values.size shouldBe 2
        }
    }
})
