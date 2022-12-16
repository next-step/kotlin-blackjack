package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerGeneratorTest : StringSpec({

    val playerGenerator = PlayerGenerator()

    "이름 수 만큼 플레이어 생성" {
        // given
        val names = listOf("kim", "lee", "park")
        val expectedSize = 3
        // when
        val actual = playerGenerator.generate(names)
        // then
        actual.size shouldBe expectedSize
    }
})
