package blackjack.domain.card

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class CardTest : DescribeSpec({
    describe("카드 생성(allShuffled)") {
        context("카드 전체를 요청하면") {
            val result = Card.allShuffled()

            it("카드 전체는 52장이다") {
                result.cards.size shouldBe 52
            }

            it("클럽 카드는 모든 랭크마다 있으며 총 13장이다") {
                val spadeCards = result.cards.filter { it.suit == Suit.CLUB }
                spadeCards.size shouldBe 13
                spadeCards.map { it.rank } shouldContainAll Rank.entries
            }

            it("다이아몬드 카드는 모든 랭크마다 있으며 총 13장이다") {
                val diamondCards = result.cards.filter { it.suit == Suit.DIAMOND }
                diamondCards.size shouldBe 13
                diamondCards.map { it.rank } shouldContainAll Rank.entries
            }

            it("하트 카드는 모든 랭크마다 있으며 총 13장이다") {
                val heartCard = result.cards.filter { it.suit == Suit.HEART }
                heartCard.size shouldBe 13
                heartCard.map { it.rank } shouldContainAll Rank.entries
            }

            it("스페이드 카드는 모든 랭크마다 있으며 총 13장이다") {
                val spadeCard = result.cards.filter { it.suit == Suit.SPADE }
                spadeCard.size shouldBe 13
                spadeCard.map { it.rank } shouldContainAll Rank.entries
            }
        }
    }
})
