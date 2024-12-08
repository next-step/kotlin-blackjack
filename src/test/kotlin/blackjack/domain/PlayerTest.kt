package blackjack.domain

import blackjack.domain.MatchResult.DRAW
import blackjack.domain.MatchResult.LOSS
import blackjack.domain.MatchResult.WIN
import blackjack.domain.Suit.CLUB
import blackjack.domain.Suit.DIAMOND
import blackjack.domain.Suit.HEART
import blackjack.domain.Suit.SPADE
import blackjack.fixtures.createCard
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어는 카드목록의 점수합이 21점 미만일 경우 카드를 더 받을 수 있다" {
        table(
            headers("ranks"),
            row(listOf("2", "3", "4")),
            row(listOf("4", "5", "10")),
            row(listOf("K", "Q")),
            row(listOf("5", "5", "4", "3", "2")),
        ).forAll { ranks ->
            val cards = Cards(ranks.map { createCard(it) })

            Player("홍길동", cards).canHit() shouldBe true
        }
    }

    "플레이어는 카드목록의 점수합이 21점 이상할 경우 카드를 더 받을 수 없다" {
        table(
            headers("ranks", "score"),
            row(listOf("J", "Q", "K"), 30),
            row(listOf("A", "K"), 21),
            row(listOf("Q", "10", "A"), 21),
            row(listOf("10", "9", "2"), 21),
            row(listOf("10", "10", "3"), 23),
        ).forAll { ranks, score ->
            val cards = Cards(ranks.map { createCard(it) })

            cards.score shouldBe score
            Player("홍길동", cards).canHit() shouldBe false
        }
    }

    "플레이어는 카드 2장을 받은 후 점수 합이 21점인 경우 카드를 더 받지 못한다" {
        val cards = Cards(emptyList())
        val player =
            Player("홍길동", cards)
                .hit(createCard("A"))
                .hit(createCard("K"))

        player.canHit() shouldBe false
    }

    "플레이어는 카드 2장을 받은 후 점수 합이 21점 미만인 경우 카드를 더 받을 수 있다" {
        val cards = Cards(emptyList())
        val player =
            Player("홍길동", cards)
                .hit(createCard("10"))
                .hit(createCard("5"))

        player.canHit() shouldBe true
    }

    "플레이어가 딜러보다 점수가 높다면 승리한다" {
        val cards = Cards(emptyList())
        val player =
            Player("홍길동", cards)
                .hit(createCard("10"))
                .hit(createCard("5"))
        val dealer = Dealer.create().hit(createCard("10"))

        player.compareScore(dealer) shouldBe WIN
    }

    "플레이어가 딜러보다 점수가 낮다면 패배한다" {
        val cards = Cards(emptyList())
        val player =
            Player("홍길동", cards)
                .hit(createCard("5"))
        val dealer =
            Dealer.create()
                .hit(createCard("10"))

        player.compareScore(dealer) shouldBe LOSS
    }

    "플레이어가 딜러보와 점수가 같다면 무승부다" {
        val cards = Cards(emptyList())
        val player =
            Player("홍길동", cards)
                .hit(createCard("5"))
        val dealer =
            Dealer.create()
                .hit(createCard("5"))

        player.compareScore(dealer) shouldBe DRAW
    }

    "플레이어의 패에 상관없이 딜러의 점수가 21점이 넘는다면 승리한다" {
        val cards = Cards(emptyList())
        val biggerScorePlayer =
            Player("김큰점수", cards)
                .hit(createCard("K", SPADE))
                .hit(createCard("Q", SPADE))
                .hit(createCard("J", SPADE))
        val smallerScorePlayer =
            Player("박작은점수", cards)
                .hit(createCard("K", HEART))
                .hit(createCard("Q", HEART))
                .hit(createCard("J", HEART))
        val sameScorePlayer =
            Player("최같은점수", cards)
                .hit(createCard("10", CLUB))
                .hit(createCard("10", CLUB))
                .hit(createCard("3", CLUB))
        val dealer =
            Dealer.create()
                .hit(createCard("10", DIAMOND))
                .hit(createCard("10", DIAMOND))
                .hit(createCard("3", DIAMOND))

        listOf(biggerScorePlayer, smallerScorePlayer, sameScorePlayer).forAll { player ->
            player.compareScore(dealer) shouldBe WIN
        }
    }
})
