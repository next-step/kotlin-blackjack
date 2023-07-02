package blakjack.domain

import blakjack.domain.extension.cards
import blakjack.domain.extension.heart10
import blakjack.domain.extension.heart2
import blakjack.domain.extension.heart3
import blakjack.domain.extension.heart9
import blakjack.domain.extension.heartAce
import blakjack.domain.extension.heartKing
import blakjack.domain.extension.spade10
import blakjack.domain.extension.spadeAce
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class ParticipantSpec : DescribeSpec({
    describe("참가자 카드 추가 검증") {
        context("참가자가 hit 하면") {
            val player = Player("홍길동")
            player.hit(heart10)

            it("카드 목록에 카드 1장이 추가된다.") {
                player.cards.size shouldBe 1
            }
            it("카드 목록에 추가한 카드가 포함된다.") {
                player.cards.values shouldBe setOf(heart10)
            }
        }
    }

    describe("참가자 카드 목록 추가 검증") {
        context("참가자에 카드 목록 1장을 추가하면") {
            val player = Player("홍길동")
            player.add(cards(heart10))

            it("카드 목록에 카드 1장이 추가된다.") {
                player.cards.size shouldBe 1
            }
            it("카드 목록에 추가한 카드가 포함된다.") {
                player.cards.values shouldBe setOf(heart10)
            }
        }

        context("참가자에 카드 목록 2장을 추가하면") {
            val player = Player("홍길동")
            player.add(cards(heart10, heart2))

            it("카드 목록에 카드 2장이 추가된다.") {
                player.cards.size shouldBe 2
            }
            it("카드 목록에 추가한 카드가 포함된다.") {
                player.cards.values shouldBe setOf(heart10, heart2)
            }
        }
    }

    describe("참가자 점수 검증") {
        context("카드 목록이 비어있으면") {
            val player = Player("홍길동")

            it("점수는 0이다.") {
                player.score shouldBe 0
            }
        }

        withData(
            nameFn = { (cards, sum) -> "카드 목록에 카드 ${cards.size}장(${cards.values.joinToString(",")})이 있을 때 점수는 ${sum}이다." },
            ts = listOf(
                cards(heart2, heart3) to 5,
                cards(heartAce, heartKing) to 21,
                cards(heartAce, heartKing, spadeAce) to 12,
                cards(heartAce, heart9, spadeAce) to 21,
                cards(heartAce, heart10, heart2) to 13,
            )
        ) { (cards, sum) ->
            val player = Player("홍길동")
            player.add(cards)

            player.score shouldBe sum
        }
    }

    describe("참가자 점수 > 블랙잭(21) 여부 검증") {
        withData(
            nameFn = { (cards, result) -> "카드 목록에 카드 ${cards.size}장(${cards.values.joinToString(",")})이 있을 때 블랙잭 이하는 ${result}이다." },
            ts = listOf(
                cards() to false,
                cards(heart2, heart3) to false,
                cards(heartAce, heartKing) to false,
                cards(heartAce, heartKing, spadeAce) to false,
                cards(heartAce, heart9, spadeAce) to false,
                cards(heartAce, heart10, heart2) to false,
                cards(heartAce, heart10, heart2, heart9) to true,
                cards(heartAce, heart10, heart2, heart9, spade10) to true,
            )
        ) { (cards, result) ->
            val player = Player("홍길동")
            player.add(cards)

            player.isOver21 shouldBe result
        }
    }

    describe("BUST 상태 검증") {
        context("점수가 21점 이하면") {
            val player = Player("홍길동")
            player.add(cards(heart10, heart2))

            it("BUST 상태가 아니다.") {
                player.isBust() shouldBe false
            }
        }

        context("점수가 21점 초과면") {
            val player = Player("홍길동")
            player.add(cards(heart10, heart9, spade10))

            it("BUST 상태이다.") {
                player.isBust() shouldBe true
            }
        }
    }
})
