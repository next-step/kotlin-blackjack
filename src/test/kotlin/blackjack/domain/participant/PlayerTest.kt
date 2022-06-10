package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardDeckTest
import blackjack.domain.card.type.Ace
import blackjack.domain.card.type.Suit
import blackjack.domain.card.type.Ten
import blackjack.domain.participant.vo.Name
import blackjack.domain.participant.vo.WinningScore
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

        player.participantInformation.isStay() shouldBe true
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

    "딜러와 비교하여 승리할수 있다." {
        val player = player()
        val reverseCardDeck = CardDeck(CardDeckTest.sortedCardDeck().cards.reversed())
        val sortedCardDeck = CardDeckTest.sortedCardDeck()

        player.ready(reverseCardDeck)

        val win = Dealer()
        win.ready(sortedCardDeck)

        player.score(listOf(win))

        player.winningScores.values shouldBe listOf(WinningScore.WIN)
    }

    "딜러와 비교하여 패배할수 있다." {
        val player = player()
        val reverseCardDeck = CardDeck(CardDeckTest.sortedCardDeck().cards.reversed())
        val sortedCardDeck = CardDeckTest.sortedCardDeck()

        player.ready(sortedCardDeck)

        val lose = Dealer()
        lose.ready(reverseCardDeck)

        player.score(listOf(lose))

        player.winningScores.values shouldBe listOf(WinningScore.LOSE)
    }

    "딜러와 비교하여 무승부할수 있다." {
        val player = player()
        val reverseCardDeck = CardDeck(CardDeckTest.sortedCardDeck().cards.reversed())

        player.ready(reverseCardDeck)

        val draw = Dealer()
        draw.ready(reverseCardDeck)

        player.score(listOf(draw))

        player.winningScores.values shouldBe listOf(WinningScore.DRAW)
    }
}) {
    companion object {
        private fun player() = Player.sit(Name("dean"))
    }
}
