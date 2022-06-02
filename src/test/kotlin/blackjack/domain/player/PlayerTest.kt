package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeckTest
import blackjack.domain.card.type.Ace
import blackjack.domain.card.type.Suit
import blackjack.domain.card.type.Ten
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "참가자 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { Player.sit(Name("dean")) }
    }

    "Stay 상태로 변경할수 있다" {
        val player = player()

        player.stay()

        player.isStay shouldBe true
    }

    "카드를 뽑을수 있다." {
        val player = player()
        val cardDeck = CardDeckTest.cardDeck()

        player.hit(cardDeck)

        player.cardsInHand.cards.size shouldBe 1
    }

    "Stay 선언시 카드를 뽑는 경우 Exception을 던진다." {
        val player = player()
        val cardDeck = CardDeckTest.cardDeck()

        player.stay()

        shouldThrow<IllegalArgumentException> { player.hit(cardDeck) }
    }

    "플레이어의 점수가 21보다 크거나 같을때 카드를 뽑으면 Exception을 던진다." {
        val player = player()
        val cardDeck = CardDeckTest.cardDeck()

        val values = listOf(
            listOf(Card(Ten(), Suit.DIAMOND), Card(Ten(), Suit.DIAMOND), Card(Ten(), Suit.DIAMOND)),
            listOf(Card(Ten(), Suit.DIAMOND), Card(Ten(), Suit.DIAMOND), Card(Ace(), Suit.DIAMOND))
        )

        values.forAll { cards ->
            cards.forEach { player.cardsInHand.add(it) }
            shouldThrow<IllegalArgumentException> { player.hit(cardDeck) }
        }
    }
}) {
    companion object {
        private fun player() = Player.sit(Name("dean"))
    }
}
