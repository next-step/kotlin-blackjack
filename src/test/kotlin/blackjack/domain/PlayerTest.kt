package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.users.Player
import blackjack.enums.Denomination
import blackjack.enums.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어는 이름과 카드리스트, 배팅 금액을 갖고 있다." {
        val cards = Cards(listOf(Card(Denomination.KING, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE)))
        val player = Player("Lee", cards, 10000)

        player.name shouldBe "Lee"
        player.cards shouldBe cards
        player.bettingAmount shouldBe 10000
    }

    "플레이어는 카드를 추가로 받을 수 있다." {
        val cards = Cards(listOf(Card(Denomination.KING, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE)))
        val player = Player("Lee", cards, 10000)

        player.plusCard(Card(Denomination.ACE, Suit.SPADE))
        player.cardValues() shouldBe 21
    }

    "플레이어의 덱의 합이 21 이상이면 덱이 완성된다." {
        val cards = Cards(
            listOf(
                Card(Denomination.KING, Suit.SPADE),
                Card(Denomination.QUEEN, Suit.SPADE),
                Card(Denomination.ACE, Suit.SPADE)
            )
        )
        val player = Player("Lee", cards, 10000)

        player.isDeckInComplete() shouldBe false
    }
})
