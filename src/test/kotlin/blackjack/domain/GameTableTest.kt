package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameTableTest : StringSpec({
    "최초 딜 시 카드를 2장 나누어준다" {
        val users =
            listOf(
                User("홍길동", Cards(emptyList())),
                User("홍길덩", Cards(emptyList())),
            )
        val initCardReceivedUsers = GameTable.dealInitCard(users, Deck.create())

        initCardReceivedUsers.forEach {
            it.cards.values.size shouldBe 2
        }
    }
})
