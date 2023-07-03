package blackjack.domain

import blackjack.enums.Denomination
import blackjack.enums.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class UserTest : StringSpec({
    "플레이어는 이름과 카드리스트, 덱 완성 여부를 갖고 있다." {
        val cards = Cards(listOf(Card(Denomination.KING, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE)))
        val user = User("Lee", cards)

        user.name shouldBe "Lee"
        user.cards shouldBe cards
        user.isDeckComplete shouldBe false
    }

    "플레이어는 카드를 추가로 받을 수 있다." {
        val cards = Cards(listOf(Card(Denomination.KING, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE)))
        val user = User("Lee", cards)

        user.addCard(Card(Denomination.ACE, Suit.SPADE))
        user.cardValues() shouldBe 21
    }

    "플레이어의 덱을 완성 시킬 수 있다." {
        val cards = Cards(listOf(Card(Denomination.KING, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE)))
        val user = User("Lee", cards)

        user.deckComplete()
        user.isDeckComplete shouldBe true
    }
})
