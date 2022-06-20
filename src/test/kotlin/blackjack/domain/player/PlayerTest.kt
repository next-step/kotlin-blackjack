package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.RandomCardDeck.Companion.DIAMOND
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FreeSpec({

    "addCard" - {

        "플레이어에게 카드가 추가되어야한다" {
            val player = Player("uju", 1000)
            val card = Card(DIAMOND, "3")
            player.addCard(card)

            player.cards.size shouldBe 1
            player.cards[0] shouldBe card
        }
    }

    "score" - {

        "플레이어가 가진 점수를 반환해야한다." {
            val player = Player("uju", 1000)
            val card = Card(DIAMOND, "3")
            player.addCard(card)

            player.score() shouldBe 3
        }
    }
})
