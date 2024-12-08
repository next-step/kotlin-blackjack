package blackjack.domain

import blackjack.fixtures.createPlayers
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameTableTest : StringSpec({
    "최초 딜 시 카드를 2장 나누어준다" {
        val players = GameTable(Deck.create()).dealInitCard(createPlayers())

        players.forEach {
            it.cards.values.size shouldBe 2
        }
    }
})
