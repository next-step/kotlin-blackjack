package blackjack

import blackjack.card.deck.BlackJackCardDeck
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    val sut = Dealer(BlackJackCardDeck())

    "주어진 갯수만큼의 카드를 나누어준다" {
        val players = listOf(Player("pobi"), Player("jason"))
        sut.provideCard(players, 1)
        players.all { it.getCards().size == 1 } shouldBe true
    }

    "나누어주려는 카드의 갯수가 0 이하이면 예외가 발생한다" {
        val players = listOf(Player("pobi"))
        shouldThrow<IllegalArgumentException> {
            sut.provideCard(players, 0)
        }
    }
})
