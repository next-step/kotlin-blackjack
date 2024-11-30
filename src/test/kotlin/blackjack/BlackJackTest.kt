package blackjack

import blackjack.domain.Card
import blackjack.domain.Cards
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

class BlackJackTest : StringSpec({
    "카드는 랭크와 문양으로 이루어진다" {
        val card = Card(Rank("3"), SPADE)
        card.rank shouldBe Rank("3")
        card.suit shouldBe SPADE
    }

    "카드의 랭크가 2~10,J,Q,K,A가 아닌 경우 예외 발생한다" {
        listOf("0", "1", "-1", "B", "D").forAll { invalidRank ->
            shouldThrow<IllegalArgumentException> { Rank(invalidRank) }
        }
    }

    "랭크가 숫자이면 점수는 숫자의 값으로 계산된다" {
        listOf("2", "3", "4", "5", "6").forAll { number ->
            Cards(listOf(createCard(rank = number))).calculateScore() shouldBe number.toInt()
        }
    }

    "랭크가 J,Q,K 이면 점수는 10점으로 계산된다" {
        listOf("J", "Q", "K").forAll { face ->
            Cards(listOf(createCard(rank = face))).calculateScore() shouldBe 10
        }
    }

    "랭크가 에이스이면 기본점수는 11점이다" {
        Cards(listOf(createCard(rank = "A"))).calculateScore() shouldBe 11
    }

    "카드들의 점수합이 21을 초과할 경우 에이스는 1점으로 보정된다" {
        table(
            headers("ranks", "expected"),
            row(listOf("A", "2", "10"), 13),
            row(listOf("A", "2", "2", "K"), 15),
            row(listOf("A", "A"), 12),
            row(listOf("A", "A", "A"), 13),
            row(listOf("A", "3", "9"), 13),
        ).forAll { ranks, expected ->
            val cards = Cards(cards = ranks.map { createCard(it) })
            cards.calculateScore() shouldBe expected
        }
    }

    "카드목록의 점수는 카드의 점수의 합이다." {
        table(
            headers("ranks", "expected"),
            row(listOf("2", "3", "4"), 9),
            row(listOf("2", "Q", "J"), 22),
            row(listOf("J", "Q", "K"), 30),
            row(listOf("J", "Q", "A"), 21),
        ).forAll { ranks, expected ->
            val cards = Cards(cards = ranks.map { createCard(it) })
            cards.calculateScore() shouldBe expected
        }
    }

    "카드의 점수합이 21점 미만일 경우 카드를 더 받을 수 있다" {
        table(
            headers("ranks"),
            row(listOf("2", "3", "4")),
            row(listOf("4", "5", "10")),
            row(listOf("K", "Q")),
            row(listOf("5", "5", "4", "3", "2")),
        ).forAll { ranks ->
            val cards = Cards(cards = ranks.map { createCard(it) })
            User(cards).canReceiveCard() shouldBe true
        }
    }
})

private fun createCard(
    rank: String,
    suit: Suit = SPADE,
): Card {
    return Card(Rank(rank), suit)
}
