package blackjack.domain

import blackjack.domain.users.Dealer
import blackjack.enums.Denomination
import blackjack.enums.Suit
import blackjack.model.UserCards
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    val name = "딜러"
    "딜러는 카드의 합이 16 이하일 경우에만 카드를 추가로 받을 수 있다." {
        forAll(
            row(Cards(listOf(Card(Denomination.TWO, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE))), true),
            row(Cards(listOf(Card(Denomination.ACE, Suit.SPADE), Card(Denomination.QUEEN, Suit.SPADE))), false),
        ) { cards, receivePossible ->
            val dealer = Dealer(UserCards(name, cards))
            dealer.cardReceivePossible() shouldBe receivePossible
        }
    }
})
