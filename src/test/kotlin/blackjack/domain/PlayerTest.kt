package blackjack.domain

import blackjack.domain.users.Player
import blackjack.enums.Denomination
import blackjack.enums.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어는 이름과 카드리스트, 덱 완성 여부를 갖고 있다." {
        val cards = Cards(listOf(Card(Denomination.KING, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE)))
        val player = Player("Lee", cards)

        player.name shouldBe "Lee"
        player.cards shouldBe cards
        player.isDeckInComplete() shouldBe true
    }

    "플레이어는 카드를 추가로 받을 수 있다." {
        val cards = Cards(listOf(Card(Denomination.KING, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE)))
        val player = Player("Lee", cards)

        player.plusCard(Card(Denomination.ACE, Suit.SPADE))
        player.cardValues() shouldBe 21
    }

    "플레이어의 덱을 완성 시킬 수 있다." {
        val cards = Cards(listOf(Card(Denomination.KING, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE)))
        val player = Player("Lee", cards)

        player.deckComplete()
        player.isDeckInComplete() shouldBe false
    }
})
