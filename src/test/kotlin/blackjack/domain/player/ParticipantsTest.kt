package blackjack.domain.player

import blackjack.domain.card.CardTest.Companion.SPADE_ACE
import blackjack.domain.card.CardTest.Companion.SPADE_JACK
import blackjack.domain.card.CardTest.Companion.SPADE_KING
import blackjack.domain.card.CardTest.Companion.SPADE_QUEEN
import blackjack.domain.card.Cards
import blackjack.domain.gamestate.running.Hit
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class ParticipantsTest : FunSpec({

    context("init") {
        test("참여자가 0명인 채로 생성될 수 없다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Participants(listOf()) }
            exception.message shouldBe "참여자은 최소 1명 이상이 있어야 한다."
        }
    }

    context("drawAllParticipants") {
        test("모든 참여자에게 드로우한다") {
            val participants = Participants(listOf(Player(Name("a"))))
            participants.drawAllParticipants { SPADE_ACE }

            participants.values[0].cards() shouldContain SPADE_ACE
        }
    }

    context("hands") {
        test("현재 플레이어들의 핸즈상태를 반환한다") {
            val participants = Participants(
                listOf(
                    Player(
                        name = Name("a"),
                        gameState = Hit(cards = Cards(setOf(SPADE_ACE, SPADE_KING)))
                    )
                )
            )

            val actual = participants.hands()

            actual[0].cards shouldContainAll setOf(SPADE_ACE, SPADE_KING)
        }
    }

    context("participantName") {
        forAll(
            row(-1),
            row(2),
        ) { input ->
            test("입력받은 위치가 범위를 벗어난 경우 예외가 발생한다.") {
                val participants = Participants(listOf(Player(Name("a"))))
                val exception = shouldThrowExactly<IllegalArgumentException> { participants.participantName(input) }
                exception.message shouldBe "해당 위치의 참여자가 없습니다."
            }
        }

        test("해당 위치의 참여자의 이름을 반환한다.") {
            val participants = Participants(listOf(Player(Name("a")), Player(Name("b"))))
            val actual = participants.participantName(1)

            actual shouldBe "b"
        }
    }

    context("participantDraw") {
        forAll(
            row(-1),
            row(2),
        ) { input ->
            test("입력받은 위치가 범위를 벗어난 경우 예외가 발생한다.") {
                val participants = Participants(listOf(Player(Name("a"))))
                val exception =
                    shouldThrowExactly<IllegalArgumentException> { participants.participantDraw(input) { SPADE_ACE } }
                exception.message shouldBe "해당 위치의 참여자가 없습니다."
            }
        }

        test("해당 위치의 참여자에게 드로우한다.") {
            val participants = Participants(listOf(Player(Name("a"))))
            val actual = participants.participantDraw(0) { SPADE_ACE }

            actual.first.name shouldBe "a"
            actual.first.cards shouldContain SPADE_ACE
            actual.second shouldBe false
        }

        test("드로우 후 참여자의 상태가 종료되었다면 다음 turn true를 반환한다.") {
            val participants =
                Participants(
                    listOf(
                        Player(
                            name = Name("a"),
                            gameState = Hit(cards = Cards(setOf(SPADE_JACK, SPADE_KING)))
                        )
                    )
                )
            val actual = participants.participantDraw(0) { SPADE_QUEEN }

            actual.first.name shouldBe "a"
            actual.first.cards shouldContainAll setOf(SPADE_JACK, SPADE_KING, SPADE_QUEEN)
            actual.second shouldBe true
        }
    }
})
