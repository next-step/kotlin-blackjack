package blackjack.step2.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class PlayersTest : FunSpec({
    context("Players 클래스 테스트") {
        test("플레이어 이름 리스트로 Players 객체를 생성할 수 있다.") {
            // Given
            val playerNames = listOf("dongyeon", "pobi", "jason")

            // When
            val players = Players.of(playerNames)

            // Then
            players.names shouldContainExactly playerNames
        }

        test("Players 객체에서 개별 플레이어의 이름을 확인할 수 있다.") {
            // Given
            val playerNames = listOf("dongyeon", "pobi")
            val players = Players.of(playerNames)

            // Then
            players.names[0] shouldBe "dongyeon"
            players.names[1] shouldBe "pobi"
        }

        test("플레이어 이름이 빈 리스트일 경우 예외가 발생한다.") {
            // Given
            val emptyNames = emptyList<String>()

            // When, Then
            shouldThrow<IllegalArgumentException> {
                Players.of(emptyNames)
            }.message shouldBe "플레이어는 한 명 이상이어야 합니다."
        }

        test("빈 이름으로 Players 객체를 생성하려고 하면 예외가 발생한다.") {
            // Given
            val invalidNames = listOf("dongyeon", "", "jason")

            // When & Then
            shouldThrow<IllegalArgumentException> {
                Players.of(invalidNames)
            }.message shouldBe "이름은 빈 값일 수 없습니다."
        }

        test("공백 이름으로 Players 객체를 생성하려고 하면 예외가 발생한다.") {
            // Given
            val invalidNames = listOf("dongyeon", " ", "jason")

            // When & Then
            shouldThrow<IllegalArgumentException> {
                Players.of(invalidNames)
            }.message shouldBe "이름은 빈 값일 수 없습니다."
        }
    }
})
