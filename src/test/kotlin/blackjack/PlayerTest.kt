package blackjack

import blackjack.CardTextFixtures.spadeFiveCard
import blackjack.CardTextFixtures.spadeJackCard
import blackjack.CardTextFixtures.spadeKingCard
import blackjack.CardTextFixtures.spadeQueenCard
import blackjack.CardTextFixtures.spadeSixCard
import blackjack.CardTextFixtures.spadeTwoCard
import blackjack.InitialCardsTestFixtures.blackjackCards
import blackjack.InitialCardsTestFixtures.initial16Cards
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class PlayerTest : BehaviorSpec({
    given("Player 를 생성할 때") {
        `when`("이름이 빈 문자열이면") {
            val name = " "
            val initialCards =
                listOf(
                    spadeKingCard,
                    spadeQueenCard,
                )
            then("IllegalArgumentException 을 던진다") {
                shouldThrow<IllegalArgumentException> {
                    Player(name = name, initialCards = initialCards)
                }
            }
        }

        `when`("시작 카드가 2장이 아니면") {
            val name = "y2gcoder"
            then("IllegalArgumentException 을 던진다") {
                listOf(
                    listOf(
                        spadeKingCard,
                    ),
                    listOf(
                        spadeJackCard,
                        spadeQueenCard,
                        spadeKingCard,
                    ),
                ).forEach { initialCards ->
                    shouldThrow<IllegalArgumentException> {
                        Player(name = name, initialCards = initialCards)
                    }
                }
            }
        }

        val name = "y2gcoder"
        `when`("시작 카드 조합이 블랙잭이면") {
            val initialCards = blackjackCards
            val sut = Player(name = name, initialCards = initialCards)

            then("Player 는 종료된 상태가 된다") {
                sut.isFinished() shouldBe true
            }

            then("Player 는 블랙잭 상태다") {
                sut.isBlackjack() shouldBe true
            }
        }

        `when`("시작 카드 조합이 블랙잭이 아니면") {
            val initialCards = initial16Cards
            val sut = Player(name = name, initialCards = initialCards)

            then("Player 는 종료된 상태가 아니다") {
                sut.isFinished() shouldBe false
            }
        }
    }

    given("Player 는 종료된 상태일 때") {
        val name = "y2gcoder"
        val initialCards = blackjackCards
        val sut = Player(name = name, initialCards = initialCards)
        val card = spadeTwoCard

        `when`("히트하려고 하면") {
            then("IllegalStateException 을 던진다") {
                sut.isFinished() shouldBe true
                shouldThrow<IllegalStateException> {
                    sut.hit(card)
                }
            }
        }
    }

    given("Player의 시작 카드가 16일 때") {
        val name = "y2gcoder"
        val initialCards = initial16Cards

        `when`("Hit 한 카드가 5라면") {
            val sut = Player(name = name, initialCards = initialCards)
            val card = spadeFiveCard
            sut.hit(card)

            then("Player 는 종료 상태가 아니다") {
                sut.isFinished() shouldBe false
            }
        }
        `when`("Hit 한 카드가 6이라면") {
            val sut = Player(name = name, initialCards = initialCards)
            val card = spadeSixCard
            sut.hit(card)

            then("Player 는 종료 상태가 된다") {
                sut.isFinished() shouldBe true
            }

            then("Player 는 Bust 상태가 된다") {
                sut.isBust() shouldBe true
            }
        }

        `when`("Stay 하면") {
            val sut = Player(name = name, initialCards = initialCards)
            sut.stay()

            then("Player 는 종료 상태가 된다") {
                sut.isFinished() shouldBe true
            }
        }
    }

    given("Player 는") {
        val name = "y2gcoder"
        val initialCards = initial16Cards

        `when`("cards 를 통해") {
            val sut = Player(name = name, initialCards = initialCards)
            val result = sut.cards

            then("자신이 가진 카드 목록을 반환한다") {
                result.toList() shouldContainExactly initialCards
            }
        }

        `when`("sumOfCards() 를 통해") {
            val sut = Player(name = name, initialCards = initialCards)
            val result: Int = sut.sumOfCards()

            then("자신이 가진 카드의 합을 반환한다") {
                result shouldBe 16
            }
        }

        `when`("1 미만을 베팅하면") {
            val sut = Player(name = name, initialCards = initialCards)
            val betAmount: Long = 1

            then("IllegalArgumentException 예외를 던진다.") {
                sut.bet(betAmount)
            }
        }
    }
})
