package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Deck
import blackjack.domain.Rank
import blackjack.domain.Suit
import blackjack.domain.Suit.SPADE
import blackjack.domain.User
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BlackJackTest : StringSpec({
    "카드는 에이스로 만들어진다면 에이스 카드이다" {
        val card = Card(Rank("A"), SPADE)
        card.isAce() shouldBe true
    }

    "카드는 스페이드로 만들어진다면 에이스 카드가 아니다" {
        val card = Card(Rank("3"), SPADE)
        card.isAce() shouldBe false
    }

    "카드의 랭크가 2~10,J,Q,K,A가 아닌 경우 예외 발생한다" {
        listOf("0", "1", "-1", "B", "D").forAll { invalidRank ->
            shouldThrow<IllegalArgumentException> { Rank(invalidRank) }
        }
    }

    "랭크가 숫자이면 점수는 숫자의 값으로 계산된다" {
        listOf("2", "3", "4", "5", "6").forAll { number ->
            Rank(number).score shouldBe number.toInt()
        }
    }

    "랭크가 J,Q,K 이면 점수는 10점으로 계산된다" {
        listOf("J", "Q", "K").forAll { face ->
            Rank(face).score shouldBe 10
        }
    }

    "랭크가 에이스이면 기본점수는 11점이다" {
        Rank("A").score shouldBe 11
    }

    "카드목록의 점수합이 21을 초과할 경우 에이스는 1점으로 보정된다" {
        table(
            headers("ranks", "expected"),
            row(listOf("A", "2", "10"), 13),
            row(listOf("A", "2", "2", "K"), 15),
            row(listOf("A", "A"), 12),
            row(listOf("A", "A", "A"), 13),
            row(listOf("A", "3", "9"), 13),
        ).forAll { ranks, expected ->
            val cards = Cards(ranks.map { createCard(it) })
            cards.calculateScore() shouldBe expected
        }
    }

    "유저는 카드목록의 점수합이 21점 미만일 경우 카드를 더 받을 수 있다" {
        table(
            headers("ranks"),
            row(listOf("2", "3", "4")),
            row(listOf("4", "5", "10")),
            row(listOf("K", "Q")),
            row(listOf("5", "5", "4", "3", "2")),
        ).forAll { ranks ->
            val cards = Cards(ranks.map { createCard(it) })
            val score = cards.calculateScore()
            println(score)
            User("홍길동", cards).canReceiveCard() shouldBe true
        }
    }

    "유저는 카드목록의 점수합이 21점 이상할 경우 카드를 더 받을 수 없다" {
        table(
            headers("ranks", "score"),
            row(listOf("J", "Q", "K"), 30),
            row(listOf("A", "K"), 21),
            row(listOf("Q", "10", "A"), 21),
            row(listOf("10", "9", "2"), 21),
            row(listOf("10", "10", "3"), 23),
        ).forAll { ranks, score ->
            val cards = Cards(ranks.map { createCard(it) })
            cards.calculateScore() shouldBe score
            User("홍길동", cards).canReceiveCard() shouldBe false
        }
    }

    "유저는 카드 2장을 받은 후 점수 합이 21점인 경우 카드를 더 받지 못한다" {
        val cards = Cards(emptyList())
        val user =
            User("홍길동", cards)
                .receiveCard(createCard("A"))
                .receiveCard(createCard("K"))

        user.canReceiveCard() shouldBe false
    }

    "유저는 카드 2장을 받은 후 점수 합이 21점 미만인 경우 카드를 더 받을 수 있다" {
        val cards = Cards(emptyList())
        val user =
            User("홍길동", cards)
                .receiveCard(createCard("10"))
                .receiveCard(createCard("5"))

        user.canReceiveCard() shouldBe true
    }

    "덱에서 카드를 하나 꺼낸다" {
        val deck = Deck.create()
        val card = deck.draw()
        card shouldNotBe null
    }

    "덱에 카드가 없을 때 카드를 꺼내면 예외 발생한다" {
        val deck = Deck.create()
        repeat(52) { deck.draw() }

        shouldThrow<IllegalStateException> { deck.draw() }
    }
})

private fun createCard(
    rank: String,
    suit: Suit = SPADE,
): Card {
    return Card(Rank(rank), suit)
}
