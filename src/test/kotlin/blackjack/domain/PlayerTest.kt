package blackjack.domain

import blackjack.test.TestDeckGenerator
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({

    given("ACE, JACK 카드룰 순서대로 발급하는 덱이 주어졌을 때") {
        val nickname = Nickname("플레이어")
        val getDeck: () -> Deck = { TestDeckGenerator.generate(Symbol.SPADE withRank Rank.ACE, Symbol.SPADE withRank Rank.JACK) }

        `when`("플레이어가 카드를 한번 발급하면") {
            val player = Player(nickname, Dealer(getDeck()))

            then("총 점수는 11점이다.") {
                player.receiveCard()
                player.calculateScore() shouldBe 11
            }
        }

        `when`("플레이어가 카드를 두번 발급하면") {
            val player = Player(nickname, Dealer(getDeck()))

            then("총 점수는 21점이다.") {
                player.receiveCard()
                player.receiveCard()
                player.calculateScore() shouldBe 21
            }
        }

        `when`("플레이어가 카드를 세번 발급하면") {
            val player = Player(nickname, Dealer(getDeck()))

            then("총 점수는 12점이다.") {
                player.receiveCard()
                player.receiveCard()
                player.receiveCard()
                player.calculateScore() shouldBe 12
            }
        }
    }

    given("NINE, TEN 카드룰 순서대로 발급하는 덱이 주어졌을 때") {
        val nickname = Nickname("플레이어")
        val getDeck: () -> Deck = { TestDeckGenerator.generate(Symbol.SPADE withRank Rank.NINE, Symbol.SPADE withRank Rank.TEN) }

        `when`("플레이어가 카드를 한번 발급하면") {
            val player = Player(nickname, Dealer(getDeck()))

            then("총 점수는 9점이다.") {
                player.receiveCard()
                player.calculateScore() shouldBe 9
            }
        }

        `when`("플레이어가 카드를 두번 발급하면") {
            val player = Player(nickname, Dealer(getDeck()))

            then("총 점수는 19점이다.") {
                player.receiveCard()
                player.receiveCard()
                player.calculateScore() shouldBe 19
            }
        }

        `when`("플레이어가 카드를 세번 발급하면") {
            val player = Player(nickname, Dealer(getDeck()))

            then("예외가 발생한다.") {
                shouldThrow<IllegalStateException> {
                    player.receiveCard()
                    player.receiveCard()
                    player.receiveCard()
                }
            }
        }
    }
})
